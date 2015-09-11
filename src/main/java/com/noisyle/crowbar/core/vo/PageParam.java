package com.noisyle.crowbar.core.vo;

public class PageParam {
	private Integer draw = 1;
	private Integer length = 10;
	
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
}
