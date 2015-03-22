package com.aggrepoint.demo.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysRightCatDao;
import com.aggrepoint.demo.dao.SysRightDao;
import com.aggrepoint.demo.dao.SysUserRightDao;
import com.aggrepoint.demo.svc.SysRightCatService;

/**
 * Service implementation for SysRightCat
 *
 * @author Jim
 */
@Service
@Transactional
public class SysRightCatServiceImpl implements SysRightCatService {
	@Autowired
	@DefaultDao
	private SysRightCatDao dao;
	@Autowired
	@Qualifier("sysRightDao")
	private SysRightDao rightDao;
	@Autowired
	@Qualifier("sysUserRightDao")
	private SysUserRightDao userRightDao;

	@Override
	public int deleteCascade(int id) {
		userRightDao.deleteByRightCat(id);
		rightDao.deleteByRightCat(id);
		dao.deleteById(id);
		return 0;
	}
}