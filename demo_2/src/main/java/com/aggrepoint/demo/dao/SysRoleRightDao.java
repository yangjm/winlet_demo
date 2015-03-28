package com.aggrepoint.demo.dao;

import java.util.List;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.demo.domain.SysRoleRight;

/**
 * Dao interface for table SYS_ROLE_RIGHTS
 *
 * @author Jim Yang
 */
public interface SysRoleRightDao extends DaoService<SysRoleRight> {
	@Find("from SysRoleRight where roleId in :1")
	default List<SysRoleRight> findByRoles(Integer[] roleId) {
		return null;
	}

	@Delete("delete from SysRoleRight where roleId = :1 and rightId in :2")
	default int delete(int roleId, Integer[] rightid) {
		return 0;
	}

	@Find("from SysRoleRight where roleId = :1 and rightId = :2")
	default SysRoleRight find(int roleid, int rightid) {
		return null;
	}
}