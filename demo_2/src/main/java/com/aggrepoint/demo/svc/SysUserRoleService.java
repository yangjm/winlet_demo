package com.aggrepoint.demo.svc;

import com.aggrepoint.demo.dao.SysUserRoleDao;

/**
 * Service interface for SysUserRole
 *
 * @author Jim
 */
public interface SysUserRoleService extends SysUserRoleDao {
	/**
	 * 给用户账号添加角色
	 * 
	 * @param roleId
	 * @param rightId
	 */
	int add(Integer[] users, Integer[] roles);

	/**
	 * 移动角色
	 * 
	 * @param from
	 * @param to
	 * @param rightid
	 */
	void move(int from, int to, int roleId);
}