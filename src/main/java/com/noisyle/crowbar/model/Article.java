package com.noisyle.crowbar.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.noisyle.crowbar.core.base.BaseModel;

public class Article extends BaseModel {

	private String title;
	private String subtitle;
	private String content;
	@DBRef
	private User author;
	@DBRef
	private Category category;
	private Date publishtime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
}
