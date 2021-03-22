package com.cafe24.kangk0269.dto;

public class MemberRestDTO {
	private int mRestIdx;	
	private String mId;
	private String mRestStartDate;
	private String mRestEndDate;
	public int getmRestIdx() {
		return mRestIdx;
	}
	public void setmRestIdx(int mRestIdx) {
		this.mRestIdx = mRestIdx;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmRestStartDate() {
		return mRestStartDate;
	}
	public void setmRestStartDate(String mRestStartDate) {
		this.mRestStartDate = mRestStartDate;
	}
	public String getmRestEndDate() {
		return mRestEndDate;
	}
	public void setmRestEndDate(String mRestEndDate) {
		this.mRestEndDate = mRestEndDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberRestDTO [mRestIdx=");
		builder.append(mRestIdx);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", mRestStartDate=");
		builder.append(mRestStartDate);
		builder.append(", mRestEndDate=");
		builder.append(mRestEndDate);
		builder.append("]");
		return builder.toString();
	}
	

}
