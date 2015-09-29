package com.noisyle.crowbar.core.auth;

public interface IUser {
	public String getId();
	public String getLoginname();
	public String getUsername();
	public String getPassword();
	public String getRole();
	public String getAvatarId();
}
