package com.footstamp.bean;

import java.util.ArrayList;

public class ProfilePageBean {
	private MemberBean memberBean;
	private ArrayList<DiaryBean> diaryList;
	private String followCnt;
	public ProfilePageBean() {
		super();
	}
	
	public ProfilePageBean(MemberBean memberBean,
			ArrayList<DiaryBean> diaryList, String followCnt) {
		super();
		this.memberBean = memberBean;
		this.diaryList = diaryList;
		this.followCnt = followCnt;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	public ArrayList<DiaryBean> getDiaryList() {
		return diaryList;
	}
	public void setDiaryList(ArrayList<DiaryBean> diaryList) {
		this.diaryList = diaryList;
	}
	public String getFollowCnt() {
		return followCnt;
	}
	public void setFollowCnt(String followCnt) {
		this.followCnt = followCnt;
	}
	
	
}
