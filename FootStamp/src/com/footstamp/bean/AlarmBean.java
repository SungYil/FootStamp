package com.footstamp.bean;

public class AlarmBean {
	private String sender;
	private String receiver;
	private String type;
	private String contend;
	private String date;
	private String isRead;
	public AlarmBean() {
		super();
	}
	public AlarmBean(String sender, String receiver, String type,
			String contend, String date, String isRead) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.type = type;
		this.contend = contend;
		this.date = date;
		this.isRead = isRead;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContend() {
		return contend;
	}
	public void setContend(String contend) {
		this.contend = contend;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	@Override
	public String toString() {
		return "AlarmBean [sender=" + sender + ", receiver=" + receiver
				+ ", type=" + type + ", contend=" + contend + ", date=" + date
				+ ", isRead=" + isRead + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmBean other = (AlarmBean) obj;
		if (contend == null) {
			if (other.contend != null)
				return false;
		} else if (!contend.equals(other.contend))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (isRead == null) {
			if (other.isRead != null)
				return false;
		} else if (!isRead.equals(other.isRead))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
