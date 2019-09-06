package com.footstamp.bean;

public class FollowBean {
	/**
	 * 상대 아이디
	 */
	private String id;
	/**
	 * 상대 이미지
	 */
	private String img;
	private String isFollowing;
	/**
	 * 나의아이디
	 */
	private String userId;
	
	public FollowBean() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getIsFollowing() {
		return isFollowing;
	}
	public void setIsFollowing(String isFollowing) {
		this.isFollowing = isFollowing;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
