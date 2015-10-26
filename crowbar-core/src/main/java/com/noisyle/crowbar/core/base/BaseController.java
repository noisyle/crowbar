package com.noisyle.crowbar.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.crowbar.core.vo.UserContext;

public abstract class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected UserContext userContext = null;
	
	public UserContext getUserContext() {
		return userContext;
	}
	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
	
}