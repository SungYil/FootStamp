package com.footstamp.model;

import java.io.Serializable;

public class TagNameDTO implements Serializable{
	private String storyId;
	private String tag;
	public TagNameDTO() {
		super();
	}
	public TagNameDTO(String storyId, String tag) {
		super();
		this.storyId = storyId;
		this.tag = tag;
	}
	public String getStoryId() {
		return storyId;
	}
	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
