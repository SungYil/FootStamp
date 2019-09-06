package com.footstamp.bean;

import java.util.Arrays;

public class DetailStorySaveBean {
	private String postion;
	private String latitude;
	private String longitude;
	private String date;
	private String weather;
	private String storyImgId;
	private String content;
	private String open;
	private String share;
	private String[] tags;
	private String writerId;
	
	public DetailStorySaveBean() {
		super();
	}
	
	public DetailStorySaveBean(String postion, String latitude,
			String longitude, String date, String weather, String storyImgId, String content,
			String open, String share, String[] tags,
			String writerId) {
		super();
		this.postion = postion;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
		this.weather = weather;
		this.storyImgId = storyImgId;
		this.content = content;
		this.open = open;
		this.share = share;
		this.tags = tags;
		this.writerId = writerId;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
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

	public String getStoryImgId() {
		return storyImgId;
	}

	public void setStoryImgId(String storyImgId) {
		this.storyImgId = storyImgId;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
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

	@Override
	public String toString() {
		return "DetailStorySaveBean [postion=" + postion + ", latitude="
				+ latitude + ", longitude=" + longitude + ", date=" + date
				+ ", weather=" + weather + ", storyImgId=" + storyImgId
				+ ", content=" + content + ", open=" + open + ", share="
				+ share + ", tags=" + Arrays.toString(tags) + ", writerId=" + writerId + "]";
	}
	
	
}
