package com.noisyle.crowbar.model;

import com.noisyle.crowbar.core.base.BaseModel;

public class Category extends BaseModel {
	private String parentId;
	private String categoryName;
	private boolean leaf;
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
}
