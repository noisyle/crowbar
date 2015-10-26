package com.noisyle.crowbar.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract public class BaseAutoTask implements Runnable {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected String parameter = null;
	
	public void setParameter(String parameter){
		this.parameter = parameter;
	}
}
