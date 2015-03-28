package com.aggrepoint.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.demo.domain.SysRole;
import com.aggrepoint.demo.svc.SysRightCatService;
import com.aggrepoint.demo.svc.SysRoleRightService;
import com.aggrepoint.demo.svc.SysRoleService;
import com.aggrepoint.winlet.form.Form;
import com.aggrepoint.winlet.spring.annotation.AccessRule;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;

/**
 * 角色管理
 * 
 * @author jiangmingyang
 */
@Winlet("role")
@AccessRule("hasRight(\"sys.role\")")
public class SysRoleController {
	@Autowired
	SysRoleService svc;
	@Autowired
	SysRightCatService catSvc;
	@Autowired
	SysRoleRightService roleRightSvc;

	// //////////////////////////////////////////////////////////////////////////
	//
	// 角色窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	@Window
	@Return(value = "list", log = "显示角色列表")
	public String listWin(
			@RequestParam(value = "name", required = false) String name,
			Model model) {
		model.addAttribute("ROLES", svc.findWithRightCats(name, 1, 1000)
				.getList());

		return "list";
	}

	@Action
	@Return(value = "edit", log = "添加或编辑角色", dialog = true, cache = true)
	@Return(value = "notfound", view = "", log = "找不到要编辑的角色")
	public String editRole(HttpServletRequest req, Model model,
			@RequestParam(value = "rid", required = false) Integer roleid) {
		return CommonActions.edit(model, roleid, svc::find, SysRole::new,
				"ROLE", "edit");
	}

	@Action
	@Return(value = "error", log = "数据校验没有通过", view = "")
	@Return(value = "vf_error", log = "单字段数据校验没有通过", view = "")
	@Return(value = "vf", log = "单字段数据校验通过", view = "")
	@Return(log = "成功保存角色")
	public String saveRole(Form form, @Valid SysRole role, BindingResult bresult) {
		return CommonActions.save(form, role, svc::createOrUpdate);
	}

	@Action
	@Return(log = "给角色添加权限")
	public String addRight(@RequestParam(value = "roleid") Integer[] roleId,
			@RequestParam(value = "rightid") Integer[] rightId) {
		roleRightSvc.add(roleId, rightId);

		return "";
	}

	@Action
	@Return(value = "common/info", log = "请用户选择", dialog = true, cache = true)
	@Return(log = "批量授权")
	public String assignRights(HttpServletRequest req,
			@RequestParam(value = "roleid", required = false) Integer[] roles,
			@RequestParam(value = "rights", required = false) Integer[] rights) {
		if (roles == null || rights == null) {
			if (roles == null && rights == null)
				req.setAttribute("MESSAGE", "请先选择要授权的角色和要赋予的权限。");
			else if (roles == null)
				req.setAttribute("MESSAGE", "请先选择要授权的角色。");
			else
				req.setAttribute("MESSAGE", "请先选择要赋予角色的权限。");
			return "common/info";
		}

		roleRightSvc.add(roles, rights);
		req.setAttribute("CHECKED", roles);
		return "";
	}

	@Action
	@Return(log = "从角色中删除权限")
	public String removeRight(@RequestParam(value = "roleid") Integer roleId,
			@RequestParam(value = "rightid") Integer[] rightId) {
		roleRightSvc.delete(roleId, rightId);
		return "";
	}

	@Action
	@Return(log = "在角色之间移动权限")
	public String moveRight(@RequestParam(value = "from") int from,
			@RequestParam(value = "to") int to,
			@RequestParam(value = "rightid") int rightId) {
		roleRightSvc.move(from, to, rightId);
		return "";
	}

	@Action
	@Return(value = "common/confirm", log = "请用户确认是否要删除角色", dialog = true, cache = true)
	@Return(value = "deleted", view = "", log = "角色被删除")
	public String deleteRole(HttpServletRequest req,
			@RequestParam(value = "rid", required = false) Integer roleid,
			@RequestParam(value = "confirm", required = false) Integer confirm,
			Model model) {
		return CommonActions.deleteAfterConfirm(model, roleid, confirm,
				svc::find, svc::deleteById, p -> "确认要删除角色“" + p.getName()
						+ "”吗?", "deleteRole", "rid");
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 权限选择窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	@Window
	@Return(log = "显示可赋予角色的权限")
	public String rightSelectWin(Model model,
			@RequestParam(value = "rights", required = false) Integer[] selected) {
		model.addAttribute("CATS", catSvc.findAllWithRights());
		model.addAttribute("SELECTED", selected);

		return "rightselect";
	}
}
