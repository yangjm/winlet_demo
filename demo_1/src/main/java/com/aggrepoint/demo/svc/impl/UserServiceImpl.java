package com.aggrepoint.demo.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggrepoint.dao.annotation.DefaultDao;
import com.aggrepoint.demo.dao.UserDao;
import com.aggrepoint.demo.svc.UserService;

/**
 * Service implementation for User
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	@DefaultDao
	private UserDao dao;
}