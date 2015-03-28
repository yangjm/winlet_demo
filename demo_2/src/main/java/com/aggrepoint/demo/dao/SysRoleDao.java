package com.aggrepoint.demo.dao;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Like;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.demo.domain.SysRole;

/**
 * Dao interface for table SYS_ROLES
 *
 * @author Jim Yang
 */
public interface SysRoleDao extends DaoService<SysRole> {
	@Find("from SysRole where 1 = 1 #{and name like :1} order by name")
	default PageList<SysRole> find(@Like String name, @PageNum int pageNum,
			@PageSize int pageSize) {
		return null;
	}

	@Delete("delete from SysUserRole where roleId = :1")
	@Delete("delete from SysRoleRight where roleId = :1")
	@Delete("delete from SysRole where roleId = :1")
	default void deleteById(int roleid) {
	}
}