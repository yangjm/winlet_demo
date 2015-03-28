package com.aggrepoint.demo.dao;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.dao.annotation.Replace;
import com.aggrepoint.demo.domain.SysRight;

/**
 * Dao interface for table SYS_RIGHTS
 *
 * @author Jim Yang
 */
public interface SysRightDao extends DaoService<SysRight> {
	/**
	 * 查找权限组中的权限
	 * 
	 * @param catid
	 * @return
	 */
	@Find("from SysRight where rightCatId = :1 #{order by :order :dir}")
	default PageList<SysRight> findByCat(int catid,
			@Replace("order") String order, @Replace("dir") String orderDir,
			@PageNum int pageNum, @PageSize int pageSize) {
		return null;
	}

	/**
	 * 根据权限代码加载权限
	 * 
	 * @param code
	 * @return
	 */
	@Find("from SysRight where rightCatId = :1 and rightCode = :2")
	default SysRight findByRightCode(int catId, String code) {
		return null;
	}

	/**
	 * 根据id级联删除权限
	 * 
	 * @param id
	 * @return
	 */
	@Delete("delete from SysRoleRight where rightId = :1")
	@Delete("delete SysRight where rightId = :1")
	default int deleteById(int id) {
		return 0;
	}
}