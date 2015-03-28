package com.aggrepoint.demo.dao;

import java.util.List;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.demo.domain.SysUserRole;

/**
 * Dao interface for table SYS_USER_ROLES
 *
 * @author Jim Yang
 */
public interface SysUserRoleDao extends DaoService<SysUserRole> {
	@Find("from SysUserRole where userId in :1")
	default List<SysUserRole> findByUsers(Integer[] users) {
		return null;
	}

	@Find("from SysUserRole r join fetch r.role where r.userId in :1")
	default List<SysUserRole> findByUsersWithRole(Integer[] users) {
		return null;
	}

	@Delete("delete from SysUserRole where userId = :1 and roleId in :2")
	default int delete(int userid, Integer[] roleid) {
		return 0;
	}

	@Find("from SysUserRole where userId = :1 and roleId = :2")
	default SysUserRole find(int userid, int roleid) {
		return null;
	}
}