package com.aggrepoint.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.demo.plugin.AdminUserProfile;
import com.aggrepoint.demo.svc.SysUserService;
import com.aggrepoint.winlet.UserEngine;
import com.aggrepoint.winlet.spring.annotation.AccessRule;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Window;
import com.aggrepoint.winlet.spring.annotation.Winlet;

/**
 * 用户登录和退出登录
 * 
 * @author jiangmingyang
 */
@Winlet("login")
public class LoginController {
	@Autowired
	SysUserService svc;

	// //////////////////////////////////////////////////////////////
	//
	// 登录窗口
	//
	// //////////////////////////////////////////////////////////////

	/**
	 * 显示登录窗口
	 * 
	 * @return
	 */
	@Window
	@AccessRule("anonymous")
	@Return(log = "Show login window")
	public String loginWin() {
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param loginName
	 *            登录用户名
	 * @param password
	 *            登录密码
	 * @param req
	 * @param ue
	 * @return
	 */
	@Action
	@AccessRule("anonymous")
	@Return(value = "", log = "登录成功", update = "page")
	@Return(value = "not found", view = "", log = "找不到登录账号")
	@Return(value = "password error", view = "", log = "密码不正确")
	@Return(value = "error", view = "", log = "遇到异常")
	public String login(
			@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "password", required = false) String password,
			HttpServletRequest req, UserEngine ue) {
		int ret = svc.auth(loginName, password);
		if (ret == 0) {
			ue.setUser(new AdminUserProfile(svc
					.findByLoginNameWithRights(loginName)));
			return "";
		}

		req.setAttribute("ERROR", "用户名或密码不正确.");

		switch (ret) {
		case -1:
			return "not found";
		case -2:
			return "password error";
		default:
			return "error";
		}
	}

	// //////////////////////////////////////////////////////////////
	//
	// 退出登录窗口
	//
	// //////////////////////////////////////////////////////////////

	@Window
	@AccessRule("!anonymous")
	@Return(log = "显示已登录用户信息")
	public String infoWin() {
		return "info";
	}

	@Action
	@AccessRule("!anonymous")
	@Return(log = "用户退出登录", update = "page")
	public String logout(UserEngine ue) {
		ue.setUser(null);
		return "";
	}
}
