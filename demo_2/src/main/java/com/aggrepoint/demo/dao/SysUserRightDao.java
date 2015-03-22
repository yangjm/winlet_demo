package com.aggrepoint.demo.dao;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.demo.domain.SysUserRight;

/**
 * Dao interface for table SYS_USER_RIGHTS
 *
 * @author Jim Yang
 */
public interface SysUserRightDao extends DaoService<SysUserRight> {
	/**
	 * 删除权限rightId相关的所有授权
	 * 
	 * @param rightId
	 *            权限编号
	 * @return
	 */
	@Delete("delete from SysUserRight where rightId = :1")
	default public int deleteByRight(int rightId) {
		return 0;
	}

	/**
	 * 删除权限组catId中的所有权限的授权
	 * 
	 * @param catId
	 *            权限组编号
	 * @return
	 */
	@Delete("delete from SysUserRight where rightId in (select rightId from SysRight where rightCatId = :1)")
	default public int deleteByRightCat(int catId) {
		return 0;
	}
}