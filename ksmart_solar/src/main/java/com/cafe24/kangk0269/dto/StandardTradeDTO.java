package com.cafe24.kangk0269.dto;

public class StandardTradeDTO {
	private int sTradeIdx;
	private String sTradeType;
	private int sTradePeriod;
	private String sTradeDate;
	private String sTradeUse;
	private String mId;
	public int getsTradeIdx() {
		return sTradeIdx;
	}
	public void setsTradeIdx(int sTradeIdx) {
		this.sTradeIdx = sTradeIdx;
	}
	public String getsTradeType() {
		return sTradeType;
	}
	public void setsTradeType(String sTradeType) {
		this.sTradeType = sTradeType;
	}
	public int getsTradePeriod() {
		return sTradePeriod;
	}
	public void setsTradePeriod(int sTradePeriod) {
		this.sTradePeriod = sTradePeriod;
	}
	public String getsTradeDate() {
		return sTradeDate;
	}
	public void setsTradeDate(String sTradeDate) {
		this.sTradeDate = sTradeDate;
	}
	public String getsTradeUse() {
		return sTradeUse;
	}
	public void setsTradeUse(String sTradeUse) {
		this.sTradeUse = sTradeUse;
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
		builder.append("StandardTradeDTO [sTradeIdx=");
		builder.append(sTradeIdx);
		builder.append(", sTradeType=");
		builder.append(sTradeType);
		builder.append(", sTradePeriod=");
		builder.append(sTradePeriod);
		builder.append(", sTradeDate=");
		builder.append(sTradeDate);
		builder.append(", sTradeUse=");
		builder.append(sTradeUse);
		builder.append(", mId=");
		builder.append(mId);
		builder.append("]");
		return builder.toString();
	}

}
