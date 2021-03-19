package com.cafe24.kangk0269.dto;

public class StandardDepositDTO {
	private int sDepositIdx;
	private String sDepositRate;
	private String sDepositDate;
	private String sDepositUse;
	private String mId;
	public int getsDepositIdx() {
		return sDepositIdx;
	}
	public void setsDepositIdx(int sDepositIdx) {
		this.sDepositIdx = sDepositIdx;
	}
	public String getsDepositRate() {
		return sDepositRate;
	}
	public void setsDepositRate(String sDepositRate) {
		this.sDepositRate = sDepositRate;
	}
	public String getsDepositDate() {
		return sDepositDate;
	}
	public void setsDepositDate(String sDepositDate) {
		this.sDepositDate = sDepositDate;
	}
	public String getsDepositUse() {
		return sDepositUse;
	}
	public void setsDepositUse(String sDepositUse) {
		this.sDepositUse = sDepositUse;
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
		builder.append("StandardDepositDTO [sDepositIdx=");
		builder.append(sDepositIdx);
		builder.append(", sDepositRate=");
		builder.append(sDepositRate);
		builder.append(", sDepositDate=");
		builder.append(sDepositDate);
		builder.append(", sDepositUse=");
		builder.append(sDepositUse);
		builder.append(", mId=");
		builder.append(mId);
		builder.append("]");
		return builder.toString();
	}

}
