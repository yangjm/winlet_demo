package com.aggrepoint.demo.dao;

import java.util.List;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.demo.domain.SysRightCat;

/**
 * Dao interface for table SYS_RIGHT_CATS
 *
 * @author Jim Yang
 */
public interface SysRightCatDao extends DaoService<SysRightCat> {
	/**
	 * 根据sortOrder属性对应的顺序加载所有权限组
	 * 
	 * @return
	 */
	@Find("from SysRightCat order by catCode")
	default public List<SysRightCat> findAllOrderByCode() {
		return null;
	}

	/**
	 * 根据权限组代码加载权限组
	 * 
	 * @param code
	 * @return
	 */
	@Find("from SysRightCat where catCode = :1")
	default public SysRightCat findByCatCode(String code) {
		return null;
	}

	/**
	 * 根据id删除权限组。不进行级联删除（除非数据库定义了级联删除）
	 * 
	 * @param id
	 * @return
	 */
	@Delete("delete SysRightCat where rightCatId = :1")
	default public int deleteById(int id) {
		return 0;
	}
}