package com.appdirect.appdirectobjects;


import com.appdirect.appdirectobjects.type.NoticeType;

public class EventNoticeInfo {

	private NoticeType type;

	private String message;

	public NoticeType getType() {
		return type;
	}

	public void setType(NoticeType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
