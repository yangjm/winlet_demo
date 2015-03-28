package com.aggrepoint.demo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 权限
 *
 * @author Jim
 */
@Entity
public class SysRight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rightId;

	private int rightCatId;

	@Pattern(regexp = "^\\w{1,10}$")
	private String rightCode;

	@NotEmpty
	@Size(max = 15)
	private String name;

	@Size(max = 500)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rightCatId", insertable = false, updatable = false)
	private SysRightCat cat;

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

	public int getRightCatId() {
		return rightCatId;
	}

	public void setRightCatId(int rightCatId) {
		this.rightCatId = rightCatId;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
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

	public SysRightCat getCat() {
		return cat;
	}

	public void setCat(SysRightCat cat) {
		this.cat = cat;
	}
}