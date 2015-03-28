package com.aggrepoint.demo.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysRoleRightDao;
import com.aggrepoint.demo.domain.SysRoleRight;
import com.aggrepoint.demo.svc.SysRoleRightService;

/**
 * Service implementation for SysRoleRight
 *
 * @author Jim
 */
@Service
@Transactional
public class SysRoleRightServiceImpl implements SysRoleRightService {
	@Autowired
	@DefaultDao
	private SysRoleRightDao dao;

	@Override
	public int add(Integer[] roleId, Integer[] rightId) {
		return CommonServiceImpl.addManyToMany(roleId, rightId,
				() -> dao.findByRoles(roleId), SysRoleRight::getRoleId,
				SysRoleRight::getRightId, (role, right) -> {
					SysRoleRight rr = new SysRoleRight();
					rr.setRoleId(role);
					rr.setRightId(right);
					dao.create(rr);
				});
	}

	@Override
	public void move(int from, int to, int rightid) {
		dao.delete(from, new Integer[] { rightid });
		if (dao.find(to, rightid) == null) {
			SysRoleRight rr = new SysRoleRight();
			rr.setRoleId(to);
			rr.setRightId(rightid);
			dao.create(rr);
		}
	}
}