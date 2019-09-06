package com.footstamp.bean;

import java.util.Arrays;

public class DetailStoryViewBean {
	/**
	 * 吏�紐�(�쐞�룄 寃쎈룄 �븘�떂)
	 */
	private String postion;
	private String date;
	private String weather;
	private String storyImg;
	private String content;
	private String[] tags;
	private String writerId;
	private String writerImg;
	/**
	 * �떇蹂꾪궎
	 */
	private String storyIdKey;
	private String likeCnt;
	private String reviewCnt;
	private String[] reviewContent;
	private String[] reviewWriterId;
	private String[] reviewWriterImg;
	private String[] reviewDate;
	public DetailStoryViewBean() {
		super();
	}
	
	public DetailStoryViewBean(String postion, String date, String weather,
			String storyImg, String content, String[] tags, String writerId,
			String writerImg, String storyIdKey, String likeCnt,
			String reviewCnt, String reviewContent, String reviewWriterId,
			String reviewWriterImg, String reviewDate) {
		super();
		this.postion = postion;
		this.date = date;
		this.weather = weather;
		this.storyImg = storyImg;
		this.content = content;
		this.tags = tags;
		this.writerId = writerId;
		this.writerImg = writerImg;
		this.storyIdKey = storyIdKey;
		this.likeCnt = likeCnt;
		this.reviewCnt = reviewCnt;
		this.reviewContent = new String[]{reviewContent};
		this.reviewWriterId = new String[]{reviewWriterId};
		this.reviewWriterImg = new String[]{reviewWriterImg};
		this.reviewDate = new String[]{reviewDate};
	}

	public String getPostion() {
		return postion;
	}
	public void setPostion(String postion) {
		this.postion = postion;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getStoryImg() {
		return storyImg;
	}
	public void setStoryImg(String storyImg) {
		this.storyImg = storyImg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getWriterImg() {
		return writerImg;
	}
	public void setWriterImg(String writerImg) {
		this.writerImg = writerImg;
	}
	public String getStoryIdKey() {
		return storyIdKey;
	}
	public void setStoryIdKey(String storyIdKey) {
		this.storyIdKey = storyIdKey;
	}
	public String getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(String likeCnt) {
		this.likeCnt = likeCnt;
	}
	public String getReviewCnt() {
		return reviewCnt;
	}
	public void setReviewCnt(String reviewCnt) {
		this.reviewCnt = reviewCnt;
	}
	public String[] getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String[] reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String[] getReviewWriterId() {
		return reviewWriterId;
	}
	public void setReviewWriterId(String[] reviewWriterId) {
		this.reviewWriterId = reviewWriterId;
	}
	public String[] getReviewWriterImg() {
		return reviewWriterImg;
	}
	public void setReviewWriterImg(String[] reviewWriterImg) {
		this.reviewWriterImg = reviewWriterImg;
	}
	public String[] getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String[] reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "DetailStoryViewBean [postion=" + postion + ", date=" + date
				+ ", weather=" + weather + ", storyImg=" + storyImg
				+ ", content=" + content + ", tags=" + Arrays.toString(tags)
				+ ", writerId=" + writerId + ", writerImg=" + writerImg
				+ ", storyIdKey=" + storyIdKey + ", likeCnt=" + likeCnt
				+ ", reviewCnt=" + reviewCnt + ", reviewContent="
				+ Arrays.toString(reviewContent) + ", reviewWriterId="
				+ Arrays.toString(reviewWriterId) + ", reviewWriterImg="
				+ Arrays.toString(reviewWriterImg) + ", reviewDate="
				+ Arrays.toString(reviewDate) + "]";
	}
	
	
}
