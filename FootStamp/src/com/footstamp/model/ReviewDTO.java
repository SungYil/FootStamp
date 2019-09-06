package com.footstamp.model;

import java.io.Serializable;

public class ReviewDTO implements Serializable{
	private String reviewId;
	private String content;
	private byte isLike;
	private String date;
	private String writerId;
	private byte isStory;
	private String bulletinId;
	
	public ReviewDTO() {
		super();
	}

	public ReviewDTO(String reviewId, String content, byte isLike, String date, String writerId,
			byte isStory, String bulletinId) {
		super();
		this.reviewId = reviewId;
		this.content = content;
		this.isLike = isLike;
		this.date = date;
		this.writerId = writerId;
		this.isStory = isStory;
		this.bulletinId = bulletinId;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte getIsLike() {
		return isLike;
	}

	public void setIsLike(byte isStory) {
		this.isLike = isStory;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(String bulletinId) {
		this.bulletinId = bulletinId;
	}
	

	public byte getIsStory() {
		return isStory;
	}

	public void setIsStory(byte isStory) {
		this.isStory = isStory;
	}

	public String[] getAll(){
		String[] res = {reviewId, content, String.valueOf(isLike), date, writerId, String.valueOf(isStory), 
				bulletinId};
		return res;
	}

	@Override
	public String toString() {
		return "ReviewDTO [reviewId=" + reviewId + ", content=" + content + ", isLike=" + isLike + ", date=" + date
				+ ", writerId=" + writerId + ", isStory=" + isStory + ", bulletinId=" + bulletinId + "]";
	}
	
	
	
}
