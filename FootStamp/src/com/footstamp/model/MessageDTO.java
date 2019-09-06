package com.footstamp.model;

import java.io.Serializable;

public class MessageDTO implements Serializable{
	private String date;
	private String content;
	public MessageDTO() {
		super();
	}
	public MessageDTO(String date, String content) {
		super();
		this.date = date;
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String[] getAll(){
		return new String[]{date,content};
	}
	@Override
	public String toString() {
		return "MessageDTO [date=" + date + ", content=" + content + "]";
	}
	
}
