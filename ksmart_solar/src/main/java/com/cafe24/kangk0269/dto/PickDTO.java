package com.cafe24.kangk0269.dto;

public class PickDTO {
	private int pIdx;
	private String bPlComponentCode;	
	private String bPlComponentSub;
	private String mId;
	private String pRegDate;	
	private String bTypeCode;
	public int getpIdx() {
		return pIdx;
	}
	public void setpIdx(int pIdx) {
		this.pIdx = pIdx;
	}
	public String getbPlComponentCode() {
		return bPlComponentCode;
	}
	public void setbPlComponentCode(String bPlComponentCode) {
		this.bPlComponentCode = bPlComponentCode;
	}
	public String getbPlComponentSub() {
		return bPlComponentSub;
	}
	public void setbPlComponentSub(String bPlComponentSub) {
		this.bPlComponentSub = bPlComponentSub;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getpRegDate() {
		return pRegDate;
	}
	public void setpRegDate(String pRegDate) {
		this.pRegDate = pRegDate;
	}
	public String getbTypeCode() {
		return bTypeCode;
	}
	public void setbTypeCode(String bTypeCode) {
		this.bTypeCode = bTypeCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PickDTO [pIdx=");
		builder.append(pIdx);
		builder.append(", bPlComponentCode=");
		builder.append(bPlComponentCode);
		builder.append(", bPlComponentSub=");
		builder.append(bPlComponentSub);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", pRegDate=");
		builder.append(pRegDate);
		builder.append(", bTypeCode=");
		builder.append(bTypeCode);
		builder.append("]");
		return builder.toString();
	}

}
