package com.aggrepoint.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 角色具备的权限
 *
 * @author Jim
 */
@Entity
public class SysRoleRight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleRightId;

	private int rightId;

	private int roleId;

	public int getRoleRightId() {
		return roleRightId;
	}

	public void setRoleRightId(int roleRightId) {
		this.roleRightId = roleRightId;
	}

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}