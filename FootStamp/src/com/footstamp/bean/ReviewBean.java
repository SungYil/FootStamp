package com.footstamp.bean;

public class ReviewBean {
	private String writerId;
	private String reviewId;
	private String bulletinId;
	private String isLike;
	private String isStory;
	private String date;
	private String content;
	public ReviewBean() {
		super();
	}
	public ReviewBean(String writerId, String reviewId, String bulletinId, String isLike, String isStory, String date,
			String content) {

		super();
		this.writerId = writerId;
		this.reviewId = reviewId;
		this.bulletinId = bulletinId;
		this.isLike = isLike;
		this.isStory = isStory;
		this.date = date;
		this.content = content;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getBulletinId() {
		return bulletinId;
	}
	public void setBulletinId(String bulletinId) {
		this.bulletinId = bulletinId;
	}
	public String getIsLike() {
		return isLike;
	}
	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}
	public String getIsStory() {
		return isStory;
	}
	public void setIsStory(String isStory) {
		this.isStory = isStory;
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
}