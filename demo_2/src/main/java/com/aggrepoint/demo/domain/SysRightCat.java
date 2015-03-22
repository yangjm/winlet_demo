package com.aggrepoint.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Domain class for table SYS_RIGHT_CATS
 *
 * @author Jim
 */
@Entity
public class SysRightCat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rightCatId;

	@Pattern(regexp = "^\\w{1,10}$")
	private String catCode;

	@NotEmpty
	@Size(max = 15)
	private String name;

	public int getRightCatId() {
		return rightCatId;
	}

	public void setRightCatId(int rightCatId) {
		this.rightCatId = rightCatId;
	}

	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}