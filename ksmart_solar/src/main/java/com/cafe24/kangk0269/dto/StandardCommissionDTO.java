package com.cafe24.kangk0269.dto;

public class StandardCommissionDTO {
	private int sCommissionIdx;
	private String sCommissionType;
	private String sCommissionRate;
	private String sCommissionDate;
	private String sCommissionUse;
	private String mId;
	public int getsCommissionIdx() {
		return sCommissionIdx;
	}
	public void setsCommissionIdx(int sCommissionIdx) {
		this.sCommissionIdx = sCommissionIdx;
	}
	public String getsCommissionType() {
		return sCommissionType;
	}
	public void setsCommissionType(String sCommissionType) {
		this.sCommissionType = sCommissionType;
	}
	public String getsCommissionRate() {
		return sCommissionRate;
	}
	public void setsCommissionRate(String sCommissionRate) {
		this.sCommissionRate = sCommissionRate;
	}
	public String getsCommissionDate() {
		return sCommissionDate;
	}
	public void setsCommissionDate(String sCommissionDate) {
		this.sCommissionDate = sCommissionDate;
	}
	public String getsCommissionUse() {
		return sCommissionUse;
	}
	public void setsCommissionUse(String sCommissionUse) {
		this.sCommissionUse = sCommissionUse;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StandardCommissionDTO [sCommissionIdx=");
		builder.append(sCommissionIdx);
		builder.append(", sCommissionType=");
		builder.append(sCommissionType);
		builder.append(", sCommissionRate=");
		builder.append(sCommissionRate);
		builder.append(", sCommissionDate=");
		builder.append(sCommissionDate);
		builder.append(", sCommissionUse=");
		builder.append(sCommissionUse);
		builder.append(", mId=");
		builder.append(mId);
		builder.append("]");
		return builder.toString();
	}

}
