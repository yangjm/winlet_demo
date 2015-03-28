package com.aggrepoint.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 权限组
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

	@OneToMany(mappedBy = "cat")
	private List<SysRight> rights;

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

	public SysRightCat clone() {
		SysRightCat cat = new SysRightCat();

		cat.catCode = catCode;
		cat.name = name;
		cat.rightCatId = rightCatId;

		return cat;
	}

	public List<SysRight> getRights() {
		if (rights == null)
			rights = new ArrayList<SysRight>();
		return rights;
	}
}