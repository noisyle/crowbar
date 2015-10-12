package com.noisyle.crowbar.core.vo;

import java.util.Date;

import com.noisyle.crowbar.core.base.IUser;

public class UserContext {
	private IUser user;
	private Date loginTime;
	
	public UserContext() {
	}
	
	public UserContext(IUser user) {
		this.user = user;
	}

	public IUser getUser() {
		return user;
	}
	public void setUser(IUser user) {
		this.user = user;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
