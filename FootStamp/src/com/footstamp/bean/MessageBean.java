package com.footstamp.bean;

public class MessageBean {
	private String roomId;
	private String writerId;
	private String date;
	private String content;
	private String isRead;
	
	public MessageBean() {
		super();
	}
	
	public MessageBean(String roomId, String writerId, String date,
			String content, String isRead) {
		super();
		this.roomId = roomId;
		this.writerId = writerId;
		this.date = date;
		this.content = content;
		this.isRead = isRead;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
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

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String[] getAll(){
		return new String[]{roomId,writerId,date,content,isRead};
	}

	@Override
	public String toString() {
		return "MessageBean [roomId=" + roomId + ", writerId=" + writerId
				+ ", date=" + date + ", content=" + content + ", isRead="
				+ isRead + "]";
	}
	
}
