package com.aggrepoint.demo.svc;

import com.aggrepoint.demo.dao.SysRightDao;

/**
 * Service interface for SysRight
 *
 * @author Jim
 */
public interface SysRightService extends SysRightDao {
	/**
	 * 删除权限，连带删除相关的用户授权。
	 * 
	 * @param id
	 *            要删除的权限的编号
	 * @return
	 */
	public int deleteCascade(int id);
}