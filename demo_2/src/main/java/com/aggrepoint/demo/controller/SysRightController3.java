package com.aggrepoint.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.spring.annotation.AccessRule;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Winlet;

/**
 * 权限组及权限管理 － 演示对话框中分页。
 * 
 * @author jiangmingyang
 */
@Winlet("sysright3")
@AccessRule("hasRight(\"sys.right\")")
public class SysRightController3 extends SysRightController {
	@Action
	@Return(value = "rightdialog", cache = true, dialog = true)
	public String showRights(@RequestParam("cid") Integer cid, Model model) {
		model.addAttribute("catId", cid);
		return "rightdialog";
	}
}
