package com.aggrepoint.demo.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysUserDao;
import com.aggrepoint.demo.domain.SysUser;
import com.aggrepoint.demo.svc.SysUserService;
import com.aggrepoint.utils.beanhash.HashUtils;

/**
 * Service implementation for SysUser
 *
 * @author Jim
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	@DefaultDao
	private SysUserDao dao;

	@Override
	public int auth(String name, String password) {
		if (name == null || password == null)
			return 1;

		SysUser user = dao.findByLoginName(name);
		if (user == null)
			return -1;

		if (!user.getPassword().equals(HashUtils.hash(password)))
			return -2;

		return 0;
	}

	@Override
	public SysUser findByLoginNameWithRights(String name) {
		SysUser user = dao.findByLoginName(name);
		if (user == null)
			return null;

		user.getRights().addAll(dao.findRights(user.getUserId()));
		return user;
	}
}