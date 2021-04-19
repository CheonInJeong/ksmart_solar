package com.cafe24.kangk0269.dto;


public class BoardQnaDTO {
	private int bQnaIdx;
	private String bQnaSubject;
	private String bQnaContents;
	private String mId;
	private String bQnaRegDate;
	private int bQnaViews;
	private String bQnaOpen;
	private String bQnaPassword;
	private int bQnaRefCode;
	private int bQnaReLev;
	private int bQnaReSeq;
	private String bQnaTemp;
	public int getbQnaIdx() {
		return bQnaIdx;
	}
	public void setbQnaIdx(int bQnaIdx) {
		this.bQnaIdx = bQnaIdx;
	}
	public String getbQnaSubject() {
		return bQnaSubject;
	}
	public void setbQnaSubject(String bQnaSubject) {
		this.bQnaSubject = bQnaSubject;
	}
	public String getbQnaContents() {
		return bQnaContents;
	}
	public void setbQnaContents(String bQnaContents) {
		this.bQnaContents = bQnaContents;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getbQnaRegDate() {
		return bQnaRegDate;
	}
	public void setbQnaRegDate(String bQnaRegDate) {
		this.bQnaRegDate = bQnaRegDate;
	}
	public int getbQnaViews() {
		return bQnaViews;
	}
	public void setbQnaViews(int bQnaViews) {
		this.bQnaViews = bQnaViews;
	}
	public String getbQnaOpen() {
		return bQnaOpen;
	}
	public void setbQnaOpen(String bQnaOpen) {
		this.bQnaOpen = bQnaOpen;
	}
	public String getbQnaPassword() {
		return bQnaPassword;
	}
	public void setbQnaPassword(String bQnaPassword) {
		this.bQnaPassword = bQnaPassword;
	}
	public int getbQnaRefCode() {
		return bQnaRefCode;
	}
	public void setbQnaRefCode(int bQnaRefCode) {
		this.bQnaRefCode = bQnaRefCode;
	}
	public int getbQnaReLev() {
		return bQnaReLev;
	}
	public void setbQnaReLev(int bQnaReLev) {
		this.bQnaReLev = bQnaReLev;
	}
	public int getbQnaReSeq() {
		return bQnaReSeq;
	}
	public void setbQnaReSeq(int bQnaReSeq) {
		this.bQnaReSeq = bQnaReSeq;
	}
	public String getbQnaTemp() {
		return bQnaTemp;
	}
	public void setbQnaTemp(String bQnaTemp) {
		this.bQnaTemp = bQnaTemp;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardQnaDTO [bQnaIdx=");
		builder.append(bQnaIdx);
		builder.append(", bQnaSubject=");
		builder.append(bQnaSubject);
		builder.append(", bQnaContents=");
		builder.append(bQnaContents);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bQnaRegDate=");
		builder.append(bQnaRegDate);
		builder.append(", bQnaViews=");
		builder.append(bQnaViews);
		builder.append(", bQnaOpen=");
		builder.append(bQnaOpen);
		builder.append(", bQnaPassword=");
		builder.append(bQnaPassword);
		builder.append(", bQnaRefCode=");
		builder.append(bQnaRefCode);
		builder.append(", bQnaReLev=");
		builder.append(bQnaReLev);
		builder.append(", bQnaReSeq=");
		builder.append(bQnaReSeq);
		builder.append(", bQnaTemp=");
		builder.append(bQnaTemp);
		builder.append("]");
		return builder.toString();
	}
	
}