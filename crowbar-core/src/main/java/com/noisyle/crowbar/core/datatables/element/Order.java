package com.noisyle.crowbar.core.datatables.element;

public class Order {
	private int column;
	private String dir = "asc";
	
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
}
