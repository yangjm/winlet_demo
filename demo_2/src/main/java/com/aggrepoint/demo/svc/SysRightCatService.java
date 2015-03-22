package com.aggrepoint.demo.svc;

import com.aggrepoint.demo.dao.SysRightCatDao;

/**
 * Service interface for SysRightCat
 *
 * @author Jim
 */
public interface SysRightCatService extends SysRightCatDao {
	/**
	 * 删除权限组，连带删除权限组中所有权限，以及相关的用户授权。
	 * 
	 * @param id 要删除的权限组的编号
	 * @return
	 */
	public int deleteCascade(int id);
}