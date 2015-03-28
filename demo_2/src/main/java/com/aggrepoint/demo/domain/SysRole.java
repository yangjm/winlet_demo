package com.aggrepoint.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 角色
 *
 * @author Jim
 */
@Entity
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;

	@NotEmpty
	@Size(max = 15)
	private String name;

	@Size(max = 500)
	private String description;

	@Transient
	private ArrayList<SysRightCat> rightCats;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<SysRightCat> getRightCats() {
		if (rightCats == null)
			rightCats = new ArrayList<SysRightCat>();
		return rightCats;
	}
}