package com.aggrepoint.demo.svc;

import com.aggrepoint.dao.PageList;
import com.aggrepoint.demo.dao.SysUserDao;
import com.aggrepoint.demo.domain.SysUser;

/**
 * Service interface for SysUser
 *
 * @author Jim
 */
public interface SysUserService extends SysUserDao {
	/**
	 * 验证用户登录
	 * 
	 * @param name
	 *            登录账号
	 * @param password
	 *            登录密码
	 * @return 0 - 成功 1 - 参数为空 -1 - 用户找不到 -2 - 密码不正确
	 */
	public int auth(String name, String password);

	/**
	 * 根据登录账号加载用户信息，连带加载用户权限
	 * 
	 * @param name
	 *            登录账号
	 * @return
	 */
	public SysUser findByLoginNameWithRightCodes(String name);

	/**
	 * 查找用户信息，连带加载角色
	 * 
	 * @param loginName
	 * @param email
	 * @param userName
	 * @param order
	 * @param orderDir
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageList<SysUser> findWithRoles(String loginName, String email,
			String userName, String order, String orderDir, int pageNum,
			int pageSize);
}