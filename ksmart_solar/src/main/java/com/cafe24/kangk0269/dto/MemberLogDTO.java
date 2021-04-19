package com.cafe24.kangk0269.dto;

public class MemberLogDTO {

	private int mLogIdx;
	private String mId;
	private String mLogIn;
	private String mLogOut;
	public int getmLogIdx() {
		return mLogIdx;
	}
	public void setmLogIdx(int mLogIdx) {
		this.mLogIdx = mLogIdx;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmLogIn() {
		return mLogIn;
	}
	public void setmLogIn(String mLogIn) {
		this.mLogIn = mLogIn;
	}
	public String getmLogOut() {
		return mLogOut;
	}
	public void setmLogOut(String mLogOut) {
		this.mLogOut = mLogOut;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberLogDTO [mLogIdx=");
		builder.append(mLogIdx);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", mLogIn=");
		builder.append(mLogIn);
		builder.append(", mLogOut=");
		builder.append(mLogOut);
		builder.append("]");
		return builder.toString();
	}
	
	
}
