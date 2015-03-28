package com.aggrepoint.demo.svc.impl;

import java.util.HashMap;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysUserDao;
import com.aggrepoint.demo.dao.SysUserRoleDao;
import com.aggrepoint.demo.domain.SysUser;
import com.aggrepoint.demo.svc.SysUserService;
import com.aggrepoint.utils.CollectionUtils;
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
	@Autowired
	@Qualifier("sysUserRoleDao")
	private SysUserRoleDao roleDao;

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
	public SysUser findByLoginNameWithRightCodes(String name) {
		SysUser user = dao.findByLoginName(name);
		if (user == null)
			return null;

		user.getRightCodes().addAll(dao.findRightCodes(user.getUserId()));
		return user;
	}

	@Override
	public PageList<SysUser> findWithRoles(String loginName, String email,
			String userName, String order, String orderDir, int pageNum,
			int pageSize) {
		PageList<SysUser> users = dao.find(loginName, email, userName, order,
				orderDir, pageNum, pageSize);

		if (users.getList().size() == 0)
			return users;

		HashMap<Integer, SysUser> userMap = CollectionUtils.toHashMap(
				users.getList(), SysUser::getUserId, Function.identity());

		roleDao.findByUsersWithRole(
				userMap.keySet().toArray(new Integer[userMap.size()])).forEach(
				p -> {
					userMap.get(p.getUserId()).getRoles().add(p.getRole());
				});

		return users;
	}
}