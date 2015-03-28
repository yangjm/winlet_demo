package com.aggrepoint.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggrepoint.winlet.spring.annotation.AccessRule;
import com.aggrepoint.winlet.spring.annotation.Action;
import com.aggrepoint.winlet.spring.annotation.Return;
import com.aggrepoint.winlet.spring.annotation.Winlet;

/**
 * 权限组及权限管理 － 展开模式
 * 
 * @author jiangmingyang
 */
@Winlet("sysright2")
@AccessRule("hasRight(\"sys.right\")")
public class SysRightController2 extends SysRightController {
	/**
	 * 因为权限列表被包含在分类窗口中，删除成功后无需刷新权限列表
	 */
	@Override
	@Action
	@Return(value = "deleted", view = "", log = "权限组被删除")
	public String deleteCat(
			@RequestParam(value = "cid", required = false) Integer cid,
			@RequestParam(value = "confirm", required = false) Integer confirm,
			Model model) {
		return super.deleteCat(cid, confirm, model);
	}
}
