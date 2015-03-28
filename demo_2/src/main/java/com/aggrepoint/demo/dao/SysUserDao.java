package com.aggrepoint.demo.dao;

import java.util.List;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Like;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.dao.annotation.Replace;
import com.aggrepoint.demo.domain.SysRole;
import com.aggrepoint.demo.domain.SysUser;

/**
 * Dao interface for table SYS_USERS
 *
 * @author Jim Yang
 */
public interface SysUserDao extends DaoService<SysUser> {
	@Find("from SysUser where 1 = 1 #{and loginName like :1} #{and email like :2} #{and userName like :3} #{order by :order :dir}")
	default PageList<SysUser> find(@Like String loginName, @Like String email,
			@Like String userName, @Replace("order") String order,
			@Replace("dir") String orderDir, @PageNum int pageNum,
			@PageSize int pageSize) {
		return null;
	}

	@Find("select distinct r from SysRole r left join fetch r.rights where r.roleId = :1")
	default SysRole findWithRights(int id) {
		return null;
	}

	@Delete("delete SysUserRole where userId = :1")
	@Delete("delete SysUser where userId = :1")
	default int deleteById(int id) {
		return 0;
	}

	/**
	 * 根据登录账号加载用户数据
	 * 
	 * @param name
	 *            登录账号
	 * @return
	 */
	@Find("from SysUser where loginName = :1")
	default SysUser findByLoginName(String name) {
		return null;
	}

	@Find("from SysUser where email = :1")
	default SysUser findByEmail(String email) {
		return null;
	}

	/**
	 * 加载用户userId的所有权限。权限组和权限的代码用.连接在一起
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	@Find("select distinct concat(src.catCode, '.', sr.rightCode) from SysUserRole sur, SysRoleRight srr, SysRight sr, SysRightCat src where sur.userId = :1 and sur.roleId = srr.roleId and srr.rightId = sr.rightId and sr.rightCatId = src.rightCatId")
	default List<String> findRightCodes(int userId) {
		return null;
	}
}