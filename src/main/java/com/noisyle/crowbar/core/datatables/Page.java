package com.noisyle.crowbar.core.datatables;

import java.util.List;

import com.noisyle.crowbar.core.base.BaseModel;

public class Page<T extends BaseModel> {
	
	private List<T> data;
	private long recordsTotal;
	private long recordsFiltered;
	private int draw;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
}
