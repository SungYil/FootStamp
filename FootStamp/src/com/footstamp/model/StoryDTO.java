package com.footstamp.model;

import java.io.Serializable;

public class StoryDTO implements Serializable {
	private String storyId;
	private String content;
	private String date;
	private byte isOpen;
	private byte isShare;
	private String weather;
	private String location;
	private String latitude;
	private String longitude;
	private String writerId;
	private String storyImg;
	
	public StoryDTO() {
		super();
	}

	public StoryDTO(String storyId, String content, String date, byte isOpen, byte isShare, String weather,String location,
			String latitude, String longitude, String writerId, String storyImg) {
		super();
		this.storyId = storyId;
		this.content = content;
		this.date = date;
		this.isOpen = isOpen;
		this.isShare = isShare;
		this.weather = weather;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.writerId = writerId;
		this.storyImg = storyImg;
	}

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getContent() {
		return content;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public byte getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(byte isOpen) {
		this.isOpen = isOpen;
	}

	public byte getIsShare() {
		return isShare;
	}

	public void setIsShare(byte isShare) {
		this.isShare = isShare;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getStoryImg() {
		return storyImg;
	}

	public void setStoryImg(String storyImg) {
		this.storyImg = storyImg;
	}
	public String[] getAll(){
		String[] res ={this.storyId,this.content,this.date,String.valueOf(this.isOpen), String.valueOf(this.isShare), this.weather,
				this.location,this.latitude,this.longitude,this.writerId,this.storyImg};
		return res;
	}

	@Override
	public String toString() {
		return "StoryDTO [storyId=" + storyId + ", content=" + content + ", date=" + date + ", isOpen=" + isOpen
				+ ", isShare=" + isShare + ", weather=" + weather + ", location=" + location + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", writerId=" + writerId + ", storyImg=" + storyImg + "]";
	}
	
	
}
