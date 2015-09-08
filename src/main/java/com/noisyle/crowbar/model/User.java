package com.noisyle.crowbar.model;

import com.noisyle.crowbar.core.base.BaseModel;

public class User extends BaseModel {

	private String loginname;
	private String username;
	private String password;
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
