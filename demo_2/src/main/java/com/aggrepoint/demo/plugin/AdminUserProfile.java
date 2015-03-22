package com.aggrepoint.demo.plugin;

import java.util.HashSet;

import com.aggrepoint.demo.domain.SysUser;
import com.aggrepoint.winlet.UserProfile;

public class AdminUserProfile implements UserProfile {
	private static final long serialVersionUID = 1L;

	SysUser user;
	HashSet<String> rightCats = new HashSet<String>();

	public AdminUserProfile(SysUser user) {
		this.user = user;

		for (String right : user.getRights()) {
			int idx = right.indexOf(".");
			if (idx > 0)
				rightCats.add(right.substring(0, idx));
			else
				rightCats.add(right);
		}
	}

	@Override
	public boolean isAnonymous() {
		return false;
	}

	@Override
	public String getLoginId() {
		return user.getLoginName();
	}

	@Override
	public String getName() {
		return user.getUserName();
	}

	public boolean hasRight(String code) {
		return user.getRights().contains(code);
	}

	public boolean hasRightCat(String cat) {
		return rightCats.contains(cat);
	}
}
