package com.cafe24.kangk0269.dto;

public class NotificationDTO {

	private int notIdx;
	private String notTargetId;
	private String notGiveId;
	private String notType;
	private String notEvent;
	private String notMessage;
	private String notUrl;
	private String notEventDate;
	private String notCheck;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotificationDTO [notIdx=");
		builder.append(notIdx);
		builder.append(", notTargetId=");
		builder.append(notTargetId);
		builder.append(", notGiveId=");
		builder.append(notGiveId);
		builder.append(", notType=");
		builder.append(notType);
		builder.append(", notEvent=");
		builder.append(notEvent);
		builder.append(", notMessage=");
		builder.append(notMessage);
		builder.append(", notUrl=");
		builder.append(notUrl);
		builder.append(", notEventDate=");
		builder.append(notEventDate);
		builder.append(", notCheck=");
		builder.append(notCheck);
		builder.append(", notCheckDate=");
		builder.append(notCheckDate);
		builder.append("]");
		return builder.toString();
	}
	public int getNotIdx() {
		return notIdx;
	}
	public void setNotIdx(int notIdx) {
		this.notIdx = notIdx;
	}
	public String getNotTargetId() {
		return notTargetId;
	}
	public void setNotTargetId(String notTargetId) {
		this.notTargetId = notTargetId;
	}
	public String getNotGiveId() {
		return notGiveId;
	}
	public void setNotGiveId(String notGiveId) {
		this.notGiveId = notGiveId;
	}
	public String getNotType() {
		return notType;
	}
	public void setNotType(String notType) {
		this.notType = notType;
	}
	public String getNotEvent() {
		return notEvent;
	}
	public void setNotEvent(String notEvent) {
		this.notEvent = notEvent;
	}
	public String getNotMessage() {
		return notMessage;
	}
	public void setNotMessage(String notMessage) {
		this.notMessage = notMessage;
	}
	public String getNotUrl() {
		return notUrl;
	}
	public void setNotUrl(String notUrl) {
		this.notUrl = notUrl;
	}
	public String getNotEventDate() {
		return notEventDate;
	}
	public void setNotEventDate(String notEventDate) {
		this.notEventDate = notEventDate;
	}
	public String getNotCheck() {
		return notCheck;
	}
	public void setNotCheck(String notCheck) {
		this.notCheck = notCheck;
	}
	public String getNotCheckDate() {
		return notCheckDate;
	}
	public void setNotCheckDate(String notCheckDate) {
		this.notCheckDate = notCheckDate;
	}
	private String notCheckDate;

}
