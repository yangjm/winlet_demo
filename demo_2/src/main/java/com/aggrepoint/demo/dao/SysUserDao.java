package com.aggrepoint.demo.dao;

import java.util.List;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.demo.domain.SysUser;

/**
 * Dao interface for table SYS_USERS
 *
 * @author Jim Yang
 */
public interface SysUserDao extends DaoService<SysUser> {
	/**
	 * 根据登录账号加载用户数据
	 * 
	 * @param name
	 *            登录账号
	 * @return
	 */
	@Find("from SysUser where loginName = :1")
	default public SysUser findByLoginName(String name) {
		return null;
	}

	/**
	 * 加载用户userId的所有权限。权限组和权限的代码用.连接在一起
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	@Find("select concat(src.catCode, '.', sr.rightCode) from SysUserRight sur, SysRight sr, SysRightCat src where sur.userId = :1 and sur.rightId = sr.rightId and sr.rightCatId = src.rightCatId")
	default List<String> findRights(int userId) {
		return null;
	}
}