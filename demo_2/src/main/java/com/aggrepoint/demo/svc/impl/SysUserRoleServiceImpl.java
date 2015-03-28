package com.aggrepoint.demo.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysUserRoleDao;
import com.aggrepoint.demo.domain.SysUserRole;
import com.aggrepoint.demo.svc.SysUserRoleService;

/**
 * Service implementation for SysUserRole
 *
 * @author Jim
 */
@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Autowired
	@DefaultDao
	private SysUserRoleDao dao;

	@Override
	public int add(Integer[] users, Integer[] roles) {
		return CommonServiceImpl.addManyToMany(users, roles,
				() -> dao.findByUsers(users), SysUserRole::getUserId,
				SysUserRole::getRoleId, (user, role) -> {
					SysUserRole ur = new SysUserRole();
					ur.setUserId(user);
					ur.setRoleId(role);
					dao.create(ur);
				});
	}

	@Override
	public void move(int from, int to, int roleId) {
		dao.delete(from, new Integer[] { roleId });
		if (dao.find(to, roleId) == null) {
			SysUserRole ur = new SysUserRole();
			ur.setUserId(to);
			ur.setRoleId(roleId);
			dao.create(ur);
		}
	}
}