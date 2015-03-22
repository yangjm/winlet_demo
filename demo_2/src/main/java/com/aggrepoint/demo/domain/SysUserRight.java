package com.aggrepoint.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Domain class for table SYS_USER_RIGHTS
 *
 * @author Jim
 */
@Entity
public class SysUserRight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userRightId;

	private int rightId;

	private int userId;

	public int getUserRightId() {
		return userRightId;
	}

	public void setUserRightId(int userRightId) {
		this.userRightId = userRightId;
	}

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}