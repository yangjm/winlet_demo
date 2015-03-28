package com.aggrepoint.demo.svc;

import com.aggrepoint.dao.PageList;
import com.aggrepoint.demo.dao.SysRoleDao;
import com.aggrepoint.demo.domain.SysRole;

/**
 * Service interface for SysRole
 *
 * @author Jim
 */
public interface SysRoleService extends SysRoleDao {
	/**
	 * 查找角色，连带加载权限
	 * 
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageList<SysRole> findWithRightCats(String name, int pageNum, int pageSize);
}