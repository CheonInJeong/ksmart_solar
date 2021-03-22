package com.cafe24.kangk0269.dto;

public class MemberRevokeDTO {

	private int mRevokeIdx;
	private String mId;
	private String mRevokeReason;
	private String mRevokeDate;
	private String mRevokeCancelDate;
	private String mRevokeFinalDate;
	public int getmRevokeIdx() {
		return mRevokeIdx;
	}
	public void setmRevokeIdx(int mRevokeIdx) {
		this.mRevokeIdx = mRevokeIdx;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmRevokeReason() {
		return mRevokeReason;
	}
	public void setmRevokeReason(String mRevokeReason) {
		this.mRevokeReason = mRevokeReason;
	}
	public String getmRevokeDate() {
		return mRevokeDate;
	}
	public void setmRevokeDate(String mRevokeDate) {
		this.mRevokeDate = mRevokeDate;
	}
	public String getmRevokeCancelDate() {
		return mRevokeCancelDate;
	}
	public void setmRevokeCancelDate(String mRevokeCancelDate) {
		this.mRevokeCancelDate = mRevokeCancelDate;
	}
	public String getmRevokeFinalDate() {
		return mRevokeFinalDate;
	}
	public void setmRevokeFinalDate(String mRevokeFinalDate) {
		this.mRevokeFinalDate = mRevokeFinalDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberRevokeDTO [mRevokeIdx=");
		builder.append(mRevokeIdx);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", mRevokeReason=");
		builder.append(mRevokeReason);
		builder.append(", mRevokeDate=");
		builder.append(mRevokeDate);
		builder.append(", mRevokeCancelDate=");
		builder.append(mRevokeCancelDate);
		builder.append(", mRevokeFinalDate=");
		builder.append(mRevokeFinalDate);
		builder.append("]");
		return builder.toString();
	}
	

}
