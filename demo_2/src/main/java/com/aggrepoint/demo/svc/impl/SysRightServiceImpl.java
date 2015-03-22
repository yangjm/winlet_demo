package com.aggrepoint.demo.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysRightDao;
import com.aggrepoint.demo.dao.SysUserRightDao;
import com.aggrepoint.demo.svc.SysRightService;

/**
 * Service implementation for SysRight
 *
 * @author Jim
 */
@Service
@Transactional
public class SysRightServiceImpl implements SysRightService {
	@Autowired
	@DefaultDao
	private SysRightDao dao;
	@Autowired
	@Qualifier("sysUserRightDao")
	private SysUserRightDao userRightDao;

	@Override
	public int deleteCascade(int id) {
		userRightDao.deleteByRight(id);
		return dao.deleteById(id);
	}
}