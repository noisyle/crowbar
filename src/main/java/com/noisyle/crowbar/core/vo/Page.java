package com.noisyle.crowbar.core.vo;

import java.util.List;

import com.noisyle.crowbar.core.base.BaseModel;

public class Page<T extends BaseModel> {
	
	private List<T> rows;
	private Long total;
	private Integer number;
	private Integer size = 10;
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

}
