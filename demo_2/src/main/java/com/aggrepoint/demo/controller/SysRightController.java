package com.aggrepoint.demo.controller;

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
import com.aggrepoint.winlet.spring.annotation.AccessRule;
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
@AccessRule("hasRight(\"sys.right\")")
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
	@Return(value = "notfound", view = "", log = "找不到要编辑的权限组")
	public String editCat(Model model,
			@RequestParam(value = "cid", required = false) Integer catid) {
		return CommonActions.edit(model, catid, catSvc::find, SysRightCat::new,
				"CAT", "catedit");
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

		return CommonActions.save(form, cat, catSvc::createOrUpdate);
	}

	@Action
	@Return(value = "common/confirm", log = "请用户确认是否要删除权限组", dialog = true, cache = true)
	@Return(value = "deleted", view = "", log = "权限组被删除", update = "rightListWin")
	public String deleteCat(
			@RequestParam(value = "cid", required = false) Integer cid,
			@RequestParam(value = "confirm", required = false) Integer confirm,
			Model model) {
		return CommonActions.deleteAfterConfirm(model, cid, confirm,
				catSvc::find, catSvc::deleteById, p -> "权限组“" + p.getName()
						+ "”中所有权限将被连带删除。确认要删除?", "deleteCat", "cid");
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
	@Return(value = "notfound", view = "", log = "找不到要编辑的权限")
	public String editRight(Model model,
			@RequestParam(value = "cid", required = false) Integer catId,
			@RequestParam(value = "rid", required = false) Integer rightId) {
		return CommonActions.edit(model, rightId, rightSvc::find, () -> {
			if (catId == null) // 添加权限时，必须指定所属的权限组
					return null;

				SysRight right = new SysRight();
				right.setRightCatId(catId);
				return right;
			}, "RIGHT", "rightedit");
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

		return CommonActions.save(form, right, rightSvc::createOrUpdate);
	}

	@Action
	@Return(value = "common/confirm", log = "请用户确认是否要删除权限", dialog = true, cache = true)
	@Return(value = "deleted", view = "", log = "权限被删除")
	public String deleteRight(
			@RequestParam(value = "rid", required = false) Integer rid,
			@RequestParam(value = "confirm", required = false) Integer confirm,
			Model model) {
		return CommonActions.deleteAfterConfirm(model, rid, confirm,
				rightSvc::find, rightSvc::deleteById,
				p -> "确认要删除权限“" + p.getName() + "”?", "deleteRight", "rid");
	}
}
