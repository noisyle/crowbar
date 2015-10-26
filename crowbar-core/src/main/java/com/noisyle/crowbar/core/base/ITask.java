package com.noisyle.crowbar.core.base;

public interface ITask {
	public String getId();
	public String getTaskName();
	public String getClazz();
	public String getParameter();
	public String getCron();
	public Boolean getEnable();
}
