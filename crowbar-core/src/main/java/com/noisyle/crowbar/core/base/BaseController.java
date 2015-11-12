package com.noisyle.crowbar.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.crowbar.core.vo.UserContext;

public abstract class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static ThreadLocal<UserContext> userContexts = new ThreadLocal<UserContext>();
	
	public UserContext getUserContext() {
		return userContexts.get();
	}
	public void setUserContext(UserContext userContext) {
		userContexts.set(userContext);
	}
}