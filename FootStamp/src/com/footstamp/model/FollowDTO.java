package com.footstamp.model;

import java.io.Serializable;

public class FollowDTO implements Serializable{
	private String follower;
	private String following;
	public FollowDTO() {
		super();
	}
	public FollowDTO(String follower, String following) {
		super();
		this.follower = follower;
		this.following = following;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getFollowing() {
		return following;
	}
	public void setFollowing(String following) {
		this.following = following;
	}
	@Override
	public String toString() {
		return "FollowDTO [follower=" + follower + ", following="
				+ following + "]";
	}
	
	
}
