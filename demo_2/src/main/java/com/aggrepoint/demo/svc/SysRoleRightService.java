package com.aggrepoint.demo.svc;

import com.aggrepoint.demo.dao.SysRoleRightDao;

/**
 * Service interface for SysRoleRight
 *
 * @author Jim
 */
public interface SysRoleRightService extends SysRoleRightDao {
	/**
	 * 给角色添加权限
	 * 
	 * @param roleId
	 * @param rightId
	 */
	int add(Integer[] roleId, Integer[] rightId);

	/**
	 * 移动权限
	 * 
	 * @param from
	 * @param to
	 * @param rightid
	 */
	void move(int from, int to, int rightid);
}