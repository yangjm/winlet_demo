package com.aggrepoint.demo.dao;

import com.aggrepoint.dao.DaoService;
import com.aggrepoint.dao.PageList;
import com.aggrepoint.dao.annotation.Delete;
import com.aggrepoint.dao.annotation.Find;
import com.aggrepoint.dao.annotation.Like;
import com.aggrepoint.dao.annotation.PageNum;
import com.aggrepoint.dao.annotation.PageSize;
import com.aggrepoint.dao.annotation.Replace;
import com.aggrepoint.demo.domain.User;

/**
 * Dao interface for table USERS
 */
public interface UserDao extends DaoService<User> {
	@Find("from User where 1 = 1 #{and loginId like :1} #{and name like :2} #{and title like :3} #{order by :order :dir}")
	default public PageList<User> find(@Like String loginid, @Like String name,
			@Like String title, @Replace("order") String order,
			@Replace("dir") String orderDir, @PageNum int pageNum,
			@PageSize int pageSize) {
		return null;
	}

	@Find("from User where loginId = :1")
	default public User findByLoginId(String loginid) {
		return null;
	}

	@Delete("delete User where userId = :1")
	default public int deleteById(int id) {
		return 0;
	}
}