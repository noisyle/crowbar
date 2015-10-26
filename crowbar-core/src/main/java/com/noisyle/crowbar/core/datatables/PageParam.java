package com.noisyle.crowbar.core.datatables;

import com.noisyle.crowbar.core.datatables.element.Column;
import com.noisyle.crowbar.core.datatables.element.Order;
import com.noisyle.crowbar.core.datatables.element.Search;

public class PageParam {
	private int draw = 1;
	private int start = 0;
	private int length = 10;
	private Column[] columns;
	private Search search;
	private Order[] order;

	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public Column[] getColumns() {
		return columns;
	}
	public void setColumns(Column[] columns) {
		this.columns = columns;
	}
	public Order[] getOrder() {
		return order;
	}
	public void setOrder(Order[] order) {
		this.order = order;
	}
}
