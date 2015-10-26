package com.noisyle.crowbar.model;

import java.util.Date;

import com.noisyle.crowbar.core.base.BaseModel;
import com.noisyle.crowbar.core.base.ITask;

public class AutoTask extends BaseModel implements ITask {
	private String taskName;
	private String clazz;
	private String parameter;
	private String cron;
	private Date modifyTime;
	private Boolean enable = false;
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
}
