package com.footstamp.bean;

public class LocationResultBean {
	private String latitude;
	private String longitude;
	private String storyImg;
	private String likeCnt;
	private String reviewCnt;
	/**
	 * 寃뚯떆湲� �떇蹂꾩젙蹂�
	 */
	private String id;
	
	public LocationResultBean() {
		super();
	}
	
	public LocationResultBean(String latitude, String longitude,
			String storyImg, String likeCnt, String reviewCnt, String id) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.storyImg = storyImg;
		this.likeCnt = likeCnt;
		this.reviewCnt = reviewCnt;
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStoryImg() {
		return storyImg;
	}
	public void setStoryImg(String storyImg) {
		this.storyImg = storyImg;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
