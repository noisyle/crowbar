package com.noisyle.crowbar.core.datatables.element;

import com.noisyle.crowbar.core.datatables.IFormatter;

public class Column {
	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	private Search search;
	private IFormatter formatter;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public IFormatter getFormatter() {
		return formatter;
	}
	public void setFormatter(IFormatter formatter) {
		this.formatter = formatter;
	}
}
