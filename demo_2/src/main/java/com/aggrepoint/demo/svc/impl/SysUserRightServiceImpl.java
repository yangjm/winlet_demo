package com.aggrepoint.demo.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.SysUserRightDao;
import com.aggrepoint.demo.svc.SysUserRightService;

/**
 * Service implementation for SysUserRight
 *
 * @author Jim
 */
@Service
@Transactional
public class SysUserRightServiceImpl implements SysUserRightService {
	@Autowired
	@DefaultDao
	private SysUserRightDao dao;
}