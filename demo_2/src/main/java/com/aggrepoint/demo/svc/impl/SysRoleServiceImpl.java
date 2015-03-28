package com.aggrepoint.demo.svc.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysRoleDao;
import com.aggrepoint.demo.dao.SysRoleRightDao;
import com.aggrepoint.demo.domain.SysRightCat;
import com.aggrepoint.demo.domain.SysRole;
import com.aggrepoint.demo.domain.SysRoleRight;
import com.aggrepoint.demo.svc.SysRightCatService;
import com.aggrepoint.demo.svc.SysRoleService;
import com.aggrepoint.utils.CollectionUtils;

/**
 * Service implementation for SysRole
 *
 * @author Jim
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	@DefaultDao
	private SysRoleDao dao;
	@Autowired
	@Qualifier("sysRoleRightDao")
	private SysRoleRightDao roleRightDao;
	@Autowired
	private SysRightCatService catSvc;

	@Override
	public PageList<SysRole> findWithRightCats(String name, int pageNum,
			int pageSize) {
		PageList<SysRole> roles = dao.find(name, pageNum, pageSize);

		if (roles.getList().size() == 0)
			return roles;

		// 加载角色具备的权限，按角色ID分组
		HashMap<Integer, HashSet<Integer>> rightsGroup = CollectionUtils.group(
				roleRightDao.findByRoles(CollectionUtils.toArray(roles
						.getList(), SysRole::getRoleId, new Integer[roles
						.getList().size()])), SysRoleRight::getRoleId,
				SysRoleRight::getRightId);

		HashMap<Integer, Collection<SysRightCat>> rightCats = catSvc
				.getCatsForId(rightsGroup);

		for (SysRole role : roles.getList()) {
			if (rightCats.containsKey(role.getRoleId()))
				role.getRightCats().addAll(rightCats.get(role.getRoleId()));
		}

		return roles;
	}
}