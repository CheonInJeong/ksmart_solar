package com.cafe24.kangk0269.dto;

public class MemberLevelDTO {

	private int mLevel;
	private String mLevelName;
	private String mLevelDate;
	public int getmLevel() {
		return mLevel;
	}
	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public String getmLevelName() {
		return mLevelName;
	}
	public void setmLevelName(String mLevelName) {
		this.mLevelName = mLevelName;
	}
	public String getmLevelDate() {
		return mLevelDate;
	}
	public void setmLevelDate(String mLevelDate) {
		this.mLevelDate = mLevelDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberLevelDTO [mLevel=");
		builder.append(mLevel);
		builder.append(", mLevelName=");
		builder.append(mLevelName);
		builder.append(", mLevelDate=");
		builder.append(mLevelDate);
		builder.append("]");
		return builder.toString();
	}
	
}
