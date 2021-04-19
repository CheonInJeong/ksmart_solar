package com.cafe24.kangk0269.dto;

public class NoticeDTO {
	private int noticeIdx;
	private String notSubject;
	private String notContents;
	private String mId;
	private String notRegDate;
	private int notViews;	
	private String notTemp;
	public int getNoticeIdx() {
		return noticeIdx;
	}
	public void setNoticeIdx(int noticeIdx) {
		this.noticeIdx = noticeIdx;
	}
	public String getNotSubject() {
		return notSubject;
	}
	public void setNotSubject(String notSubject) {
		this.notSubject = notSubject;
	}
	public String getNotContents() {
		return notContents;
	}
	public void setNotContents(String notContents) {
		this.notContents = notContents;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getNotRegDate() {
		return notRegDate;
	}
	public void setNotRegDate(String notRegDate) {
		this.notRegDate = notRegDate;
	}
	public int getNotViews() {
		return notViews;
	}
	public void setNotViews(int notViews) {
		this.notViews = notViews;
	}
	public String getNotTemp() {
		return notTemp;
	}
	public void setNotTemp(String notTemp) {
		this.notTemp = notTemp;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NoticeDTO [noticeIdx=");
		builder.append(noticeIdx);
		builder.append(", notSubject=");
		builder.append(notSubject);
		builder.append(", notContents=");
		builder.append(notContents);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", notRegDate=");
		builder.append(notRegDate);
		builder.append(", notViews=");
		builder.append(notViews);
		builder.append(", notTemp=");
		builder.append(notTemp);
		builder.append("]");
		return builder.toString();
	}
	
}
	