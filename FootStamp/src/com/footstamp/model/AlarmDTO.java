package com.footstamp.model;

import java.io.Serializable;

public class AlarmDTO implements Serializable{
	private String id;
	private int participant;
	private String alarmContent;
	private String date;
	private int isRead;
	public AlarmDTO() {
		super();
	}
	
	public AlarmDTO(String id, int participant, String alarmContent,
			String date, int isRead) {
		super();
		this.id = id;
		this.participant = participant;
		this.alarmContent = alarmContent;
		this.date = date;
		this.isRead = isRead;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public int getParticipant() {
		return participant;
	}
	public void setParticipant(int participant) {
		this.participant = participant;
	}
	public String getAlarmContent() {
		return alarmContent;
	}
	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int isRead() {
		return isRead;
	}
	public void setRead(int isRead) {
		this.isRead = isRead;
	}
	@Override
	public String toString() {
		return "AlarmDTO [participant=" + participant + ", alarmContent="
				+ alarmContent + ", date=" + date + ", isRead=" + isRead + "]";
	}
	
}
