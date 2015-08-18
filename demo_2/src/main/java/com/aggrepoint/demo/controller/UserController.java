package com.aggrepoint.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.demo.domain.SysUser;
import com.aggrepoint.demo.svc.SysRoleService;
import com.aggrepoint.demo.svc.SysUserRoleService;
import com.aggrepoint.demo.svc.SysUserService;
import com.aggrepoint.utils.beanhash.HashUtils;
import com.aggrepoint.winlet.form.Form;
import com.aggrepoint.winlet.spring.annotation.AccessRule;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;

/**
 * 用户管理
 * 
 * @author jiangmingyang
 */
@Winlet("user")
@AccessRule("hasRight(\"sys.user\")")
public class UserController {
	@Autowired
	SysUserService svc;
	@Autowired
	SysUserRoleService userRoleSvc;
	@Autowired
	SysRoleService roleSvc;

	// //////////////////////////////////////////////////////////////////////////
	//
	// 用户账号信息列表窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	@Window
	@Return(value = "list", log = "显示用户账号列表")
	public String listWin(
			@RequestParam(value = "sLoginName", required = false) String loginName,
			@RequestParam(value = "sEmail", required = false) String email,
			@RequestParam(value = "sUserName", required = false) String userName,
			@RequestParam(value = "page", required = false) Integer pageId,
			@RequestParam(value = "sortby", required = false) String sortBy,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "psize", required = false) Integer pageSize,
			Model model) {
		model.addAttribute("USERS", svc.findWithRoles(loginName, email,
				userName, sortBy, sort, pageId == null ? 1 : pageId,
				pageSize == null ? 10 : pageSize));
		return "list";
	}

	@Action
	@Return(value = "edit", log = "添加或编辑用户账号", dialog = true, cache = true)
	@Return(value = "notfound", view = "", log = "找不到要编辑的用户账号")
	public String editUser(Model model,
			@RequestParam(value = "uid", required = false) Integer userId) {
		return CommonActions.edit(model, userId, svc::find, SysUser::new,
				"USER", "edit");
	}

	@Action
	@Return(value = "error", log = "数据校验没有通过", view = "")
	@Return(value = "vf_error", log = "单字段数据校验没有通过", view = "")
	@Return(value = "vf", log = "单字段数据校验通过", view = "")
	@Return(log = "成功保存用户信息")
	public String saveUser(Form form, @Valid SysUser user, BindingResult bresult) {
		if (form.validate("email")) {
			SysUser u = svc.findByEmail(user.getEmail());
			if (u != null && u.getUserId() != user.getUserId())
				form.addError("email", "邮件地址已经被使用。");
		}
		if (form.validate("loginName")) {
			SysUser u = svc.findByLoginName(user.getLoginName());
			if (u != null && u.getUserId() != user.getUserId())
				form.addError("loginName", "账号已经被使用。");
		}

		if (!form.isValidateField()) { // 全表单提交时才检验密码
			if (user.getUserId() == 0
					|| !(user.getPassword().equals("") && user.getPassword2()
							.equals(""))) {
				if ("".equals(user.getPassword()))
					form.addError("password", "请输入密码。");
				else if (user.getPassword().length() < 4)
					form.addError("password", "密码长度不能少于4位。");
				else if ("".equals(user.getPassword2()))
					form.addError("password2", "请再次输入密码。");
				else if (!user.getPassword().equals(user.getPassword2()))
					form.addError("password2", "两次密码输入不一致。");
			}
		}

		return CommonActions.save(form, user, u -> {
			if (u.getUserId() == 0) { // 创建
					u.setPassword(HashUtils.hash(u.getPassword()));
					svc.create(u);
				} else { // 更新
					SysUser update = svc.find(u.getUserId());
					update.setUserName(u.getUserName());
					update.setEmail(u.getEmail());
					if (!"".equals(u.getPassword()))
						update.setPassword(HashUtils.hash(u.getPassword()));
					svc.update(update);
				}
			});
	}

	@Action
	@Return(log = "给用户账号添加角色")
	public String addRole(@RequestParam(value = "userid") Integer[] users,
			@RequestParam(value = "roleid") Integer[] roles) {
		userRoleSvc.add(users, roles);

		return "";
	}

	@Action
	@Return(value = "common/info", log = "请用户选择", dialog = true, cache = true)
	@Return(log = "批量授权")
	public String assignRoles(Model model,
			@RequestParam(value = "userid", required = false) Integer[] users,
			@RequestParam(value = "roles", required = false) Integer[] roles) {
		if (users == null || roles == null) {
			if (users == null && roles == null)
				model.addAttribute("MESSAGE", "请先选择要授权的用户账号和要赋予的角色。");
			else if (users == null)
				model.addAttribute("MESSAGE", "请先选择要授权的用户账号。");
			else
				model.addAttribute("MESSAGE", "请先选择要赋予用户账号的角色。");
			return "common/info";
		}

		userRoleSvc.add(users, roles);
		model.addAttribute("CHECKED", users);
		return "";
	}

	@Action
	@Return(log = "从用户账号中删除角色")
	public String removeRole(@RequestParam(value = "userid") Integer users,
			@RequestParam(value = "roleid") Integer[] roles) {
		userRoleSvc.delete(users, roles);
		return "";
	}

	@Action
	@Return(log = "在用户账号之间移动角色")
	public String moveRole(@RequestParam(value = "from") int from,
			@RequestParam(value = "to") int to,
			@RequestParam(value = "roleid") int roleId) {
		userRoleSvc.move(from, to, roleId);
		return "";
	}

	@Action
	@Return(value = "common/confirm", log = "请用户确认是否要删除用户账号", dialog = true, cache = true)
	@Return(value = "deleted", view = "", log = "用户账号被删除")
	public String deleteUser(
			@RequestParam(value = "uid", required = false) Integer userid,
			@RequestParam(value = "confirm", required = false) Integer confirm,
			Model model) {
		return CommonActions.deleteAfterConfirm(model, userid, confirm,
				svc::find, svc::deleteById,
				p -> "确认要删除用户账号“" + p.getLoginName() + "”吗?", "deleteUser",
				"uid");
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 角色选择窗口
	//
	// //////////////////////////////////////////////////////////////////////////
	@Window
	@Return(log = "显示可赋予用户的角色")
	public String rolesWin(Model model,
			@RequestParam(value = "roles", required = false) Integer[] selected) {
		model.addAttribute("ROLES", roleSvc.find());
		model.addAttribute("SELECTED", selected);

		return "roleselect";
	}
}
