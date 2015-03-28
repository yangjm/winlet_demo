package com.aggrepoint.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户
 *
 * @author Jim
 */
@Entity
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Email
	@NotEmpty
	private String email;

	@Pattern(regexp = "^[a-zA-Z]\\w{3,14}$")
	private String loginName;

	@Size(max = 50)
	private String password;

	@NotEmpty
	@Size(max = 15)
	private String userName;

	@Transient
	@Size(max = 50)
	private String password2;

	@Transient
	private ArrayList<SysRole> roles;

	@Transient
	private HashSet<String> rightCodes;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public HashSet<String> getRightCodes() {
		if (rightCodes == null)
			rightCodes = new HashSet<String>();
		return rightCodes;
	}

	public ArrayList<SysRole> getRoles() {
		if (roles == null)
			roles = new ArrayList<SysRole>();

		return roles;
	}
}