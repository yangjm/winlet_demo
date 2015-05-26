package com.aggrepoint.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.demo.domain.User;
import com.aggrepoint.demo.svc.UserService;
import com.aggrepoint.winlet.PageStorage;
import com.aggrepoint.winlet.form.Form;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;

@Winlet("user")
public class UserController {
	@Autowired
	UserService service;

	// //////////////////////////////////////////////////////////////////////////
	//
	// 用户信息列表窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	@Window
	@Return(log = "显示用户信息列表")
	public String listWin(
			@RequestParam(value = "searchName", required = false) String name,
			@RequestParam(value = "searchTitle", required = false) String title,
			@RequestParam(value = "searchLogin", required = false) String login,
			@RequestParam(value = "page", required = false) Integer pageId,
			@RequestParam(value = "sortby", required = false) String sortBy,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "psize", required = false) Integer pageSize,
			Model model) {
		model.addAttribute("USERS", service.find(login, name, title, sortBy,
				sort, pageId == null ? 1 : pageId, pageSize == null ? 10
						: pageSize));
		return "list";
	}

	@Action
	@Return(value = "", update = "editWin", log = "成功删除用户信息")
	@Return(value = "notfound", view = "", log = "找不到要删除的用户信息")
	public String delUser(@RequestParam("uid") int userId) {
		return service.deleteById(userId) > 0 ? "" : "notfound";
	}

	@Action
	@Return(update = "editWin", cache = true, log = "启动添加或者编辑用户信息")
	public String editUser(PageStorage ps, @RequestParam("uid") int uid)
			throws Exception {
		ps.setAttribute("EDITUSERID", uid);
		return "";
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// 用户信息编辑窗口
	//
	// //////////////////////////////////////////////////////////////////////////

	@Window
	@Return(value = "", log = "用户信息编辑窗口不用显示")
	@Return(value = "edit", log = "显示用户窗口")
	@Return(value = "notfound", log = "找不到要编辑的用户", view = "")
	public String editWin(PageStorage ps, Model model) {
		Integer userId = (Integer) ps.getAttribute("EDITUSERID");
		if (userId == null)
			return "";
		else
			ps.removeAttribute("EDITUSERID");

		User user = null;

		if (userId <= 0) { // userId的值<=0表示添加用户信息
			user = new User();
		} else { // 编辑用户信息
			user = service.find(userId);
			if (user == null)
				return "notfound";
		}

		model.addAttribute("USER", user);
		return "edit";
	}

	@Action
	@Return(value = "", log = "成功保存用户信息编辑", update = "listWin")
	@Return(value = "vf", log = "AJAX表单字段校验通过", view = "")
	@Return(value = "vf_error", log = "AJAX表单字段校验出错", view = "")
	@Return(value = "error", log = "表单数据校验出错", view = "")
	public String save(@Valid User user, BindingResult bresult, Form form) {
		if (form.validate("loginId")) {
			// 要对输入的loginId进行校验（或者是用户输入了loginId后触发了单字段校验，或者是用户提交整个表单后需要对所有字段进行校验）
			User u = service.findByLoginId(user.getLoginId());
			if (u != null && u.getUserId() != user.getUserId())
				form.addError("loginId", "账号已被使用。");
		}

		if (form.isValidateField())
			return form.hasError() ? "vf_error" : "vf";

		if (form.hasError())
			return "error";

		service.createOrUpdate(user);
		return "";
	}
}
