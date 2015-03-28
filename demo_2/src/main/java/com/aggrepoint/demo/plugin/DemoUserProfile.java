package com.aggrepoint.demo.plugin;

import java.util.HashSet;

import com.aggrepoint.demo.domain.SysUser;
import com.aggrepoint.winlet.UserProfile;

public class DemoUserProfile implements UserProfile {
	private static final long serialVersionUID = 1L;

	SysUser user;
	HashSet<String> rightCats = new HashSet<String>();

	public DemoUserProfile(SysUser user) {
		this.user = user;

		for (String right : user.getRightCodes()) {
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
		return user.getRightCodes().contains(code);
	}

	public boolean hasRightCat(String cat) {
		return rightCats.contains(cat);
	}
}
