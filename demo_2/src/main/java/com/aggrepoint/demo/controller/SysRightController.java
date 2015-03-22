package com.aggrepoint.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.demo.domain.SysRight;
import com.aggrepoint.demo.domain.SysRightCat;
import com.aggrepoint.demo.svc.SysRightCatService;
import com.aggrepoint.demo.svc.SysRightService;
import com.aggrepoint.winlet.form.Form;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;

/**
 * 权限组及权限管理
 * 
 * @author jiangmingyang
 */
@Winlet("sysright")
public class SysRightController {
	@Autowired
	SysRightCatService catSvc;
	@Autowired
	SysRightService rightSvc;

	// //////////////////////////////////////////////////////////////
	//
	// 权限组
	//
	// //////////////////////////////////////////////////////////////

	@Window
	@Return(log = "显示权限组列表")
	public String catListWin(Model model) {
		model.addAttribute("CATS", catSvc.findAllOrderByCode());
		return "catlist";
	}

	@Action
	@Return(value = "catedit", log = "添加或编辑权限组", dialog = true, cache = true)
	public String editCat(HttpServletRequest req, Model model,
			@RequestParam(value = "cid", required = false) Integer catid) {
		if (catid == null)
			return "";

		model.addAttribute("CAT",
				catid == 0 ? new SysRightCat() : catSvc.find(catid));
		return "catedit";
	}

	@Action
	@Return(value = "error", log = "数据校验没有通过", view = "")
	@Return(value = "vf_error", log = "单字段数据校验没有通过", view = "")
	@Return(value = "vf", log = "单字段数据校验通过", view = "")
	@Return(log = "成功保存权限组")
	public String saveCat(Form form, @Valid SysRightCat cat,
			BindingResult bresult) {
		if (form.validate("catCode")) {
			// 要对输入的catCode进行校验（或者是用户输入了catCode后触发了单字段校验，或者是用户提交整个表单后需要对所有字段进行校验）
			SysRightCat c = catSvc.findByCatCode(cat.getCatCode());
			if (c != null && c.getRightCatId() != cat.getRightCatId())
				form.addError("catCode", "权限组代码已经被使用。");
		}

		if (form.isValidateField())
			return form.hasError() ? "vf_error" : "vf";

		if (form.hasError())
			return "error";

		catSvc.createOrUpdate(cat);

		return "";
	}

	@Action
	@Return(value = "common/confirm", log = "请用户确认是否要删除权限组", dialog = true, cache = true)
	@Return(value = "deleted", view = "", log = "权限组被删除", update = "rightListWin")
	public String deleteCat(HttpServletRequest req,
			@RequestParam(value = "cid", required = false) Integer cid,
			@RequestParam(value = "confirm", required = false) Integer confirm,
			Model model) {
		if (cid == null || cid == 0) // 没有接收到权限组ID
			return "";

		SysRightCat cat = catSvc.find(cid);
		if (cat == null) // 找不到要删除的权限组
			return "";

		if (confirm != null && confirm > 0) { // 用户已经确认删除
			catSvc.deleteCascade(cid);
			return "deleted";
		} else { // 请用户确认删除
			model.addAttribute("MESSAGE", "权限组“" + cat.getName()
					+ "”中所有权限将被连带删除。确认要删除?");
			model.addAttribute("ACTION", "deleteCat");
			model.addAttribute("PARAMS", "cid: " + cid + ", confirm: 1");
			return "common/confirm";
		}
	}

	// //////////////////////////////////////////////////////////////
	//
	// 权限
	//
	// //////////////////////////////////////////////////////////////

	@Window
	@Return(value = "nosel", view = "", log = "没有被选中的权限组，不显示权限列表")
	@Return(log = "显示权限列表")
	public String rightListWin(
			@RequestParam(value = "selcat", required = false) Integer cid,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "dir", required = false) String dir,
			@RequestParam(value = "rpage", required = false) Integer page,
			@RequestParam(value = "rpsize", required = false) Integer pageSize,
			Model model) {
		if (cid == null || catSvc.find(cid) == null)
			return "nosel";

		model.addAttribute("RIGHTS", rightSvc.findByCat(cid,
				sort == null ? "rightCode" : sort, dir == null ? "asc" : dir,
				page == null ? 1 : page, pageSize == null ? 10 : pageSize));
		return "rightlist";
	}

	@Action
	@Return(value = "rightedit", log = "添加或编辑权限", dialog = true, cache = true)
	public String editRight(HttpServletRequest req, Model model,
			@RequestParam(value = "cid", required = false) Integer catId,
			@RequestParam(value = "rid", required = false) Integer rightId) {
		if (rightId == null)
			return "";

		SysRight right = null;

		if (rightId == 0) {
			if (catId == null) // 添加权限时，必须指定所属的权限组
				return "";

			right = new SysRight();
			right.setRightCatId(catId);
		} else
			right = rightSvc.find(rightId);

		model.addAttribute("RIGHT", right);
		return "rightedit";
	}

	@Action
	@Return(value = "error", log = "数据校验没有通过", view = "")
	@Return(value = "vf_error", log = "单字段数据校验没有通过", view = "")
	@Return(value = "vf", log = "单字段数据校验通过", view = "")
	@Return(log = "成功保存权限")
	public String saveRight(Form form, @Valid SysRight right,
			BindingResult bresult) {
		if (form.validate("rightCode")) {
			// 要对输入的rightCode进行校验（或者是用户输入了rightCode后触发了单字段校验，或者是用户提交整个表单后需要对所有字段进行校验）
			SysRight r = rightSvc.findByRightCode(right.getRightCatId(),
					right.getRightCode());
			if (r != null && r.getRightId() != right.getRightId())
				form.addError("rightCode", "权限代码已经被使用。");
		}

		if (form.isValidateField())
			return form.hasError() ? "vf_error" : "vf";

		if (form.hasError())
			return "error";

		rightSvc.createOrUpdate(right);

		return "";
	}

	@Action
	@Return(value = "common/confirm", log = "请用户确认是否要删除权限", dialog = true, cache = true)
	@Return(value = "deleted", view = "", log = "权限被删除")
	public String deleteRight(HttpServletRequest req,
			@RequestParam(value = "rid", required = false) Integer rid,
			@RequestParam(value = "confirm", required = false) Integer confirm,
			Model model) {
		if (rid == null || rid == 0) // 没有接收到权限ID
			return "";

		SysRight right = rightSvc.find(rid);
		if (right == null) // 找不到要删除的权限
			return "";

		if (confirm != null && confirm > 0) { // 用户已经确认删除
			rightSvc.deleteCascade(rid);
			return "deleted";
		} else { // 请用户确认删除
			model.addAttribute("MESSAGE", "确认要删除权限“" + right.getName() + "”?");
			model.addAttribute("ACTION", "deleteRight");
			model.addAttribute("PARAMS", "rid: " + rid + ", confirm: 1");
			return "common/confirm";
		}
	}
}
