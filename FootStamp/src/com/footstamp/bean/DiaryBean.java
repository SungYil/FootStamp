package com.footstamp.bean;

public class DiaryBean {
	private String[][]stories;
	private String likeCnt;
	private String reviewCnt;	
	private String diaryId;
	
	public DiaryBean() {
		super();
	}

	public DiaryBean(String[][] stories, String likeCnt, String reviewCnt,
			String diaryId) {
		super();
		this.stories = stories;
		this.likeCnt = likeCnt;
		this.reviewCnt = reviewCnt;
		this.diaryId = diaryId;
	}

	public String[][] getStories() {
		return stories;
	}

	public void setStories(String[][] stories) {
		this.stories = stories;
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

	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}
	
}
