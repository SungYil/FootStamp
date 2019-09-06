package com.footstamp.bean;

public class TagResultBean {
	private String storyImg;
	/**
	 * 寃뚯떆湲� �떇蹂꾩젙蹂�
	 */
	private String id;
	private String likeCnt;
	private String reviewCnt;
	public TagResultBean() {
		super();
	}
	
	public TagResultBean(String storyImg, String id, String likeCnt,
			String reviewCnt) {
		super();
		this.storyImg = storyImg;
		this.id = id;
		this.likeCnt = likeCnt;
		this.reviewCnt = reviewCnt;
	}

	public String getStoryImg() {
		return storyImg;
	}
	public void setStoryImg(String stroyImg) {
		this.storyImg = stroyImg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
}
