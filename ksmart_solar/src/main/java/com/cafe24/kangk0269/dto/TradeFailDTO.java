package com.cafe24.kangk0269.dto;

public class TradeFailDTO {
	private String trFailCode;
	private String relatedCode;
	private String trFailType;
	private String mId;	
	private int trFailBasis;
	private double bCancelRate;
	private int bCancel;
	private String trFailDate;
	private String announcedCode;
	
	public String getAnnouncedCode() {
		return announcedCode;
	}
	public void setAnnouncedCode(String announcedCode) {
		this.announcedCode = announcedCode;
	}
	public String getTrFailCode() {
		return trFailCode;
	}
	public void setTrFailCode(String trFailCode) {
		this.trFailCode = trFailCode;
	}
	public String getRelatedCode() {
		return relatedCode;
	}
	public void setRelatedCode(String relatedCode) {
		this.relatedCode = relatedCode;
	}
	public String getTrFailType() {
		return trFailType;
	}
	public void setTrFailType(String trFailType) {
		this.trFailType = trFailType;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getTrFailBasis() {
		return trFailBasis;
	}
	public void setTrFailBasis(int trFailBasis) {
		this.trFailBasis = trFailBasis;
	}
	public double getbCancelRate() {
		return bCancelRate;
	}
	public void setbCancelRate(double bCancelRate) {
		this.bCancelRate = bCancelRate;
	}
	public int getbCancel() {
		return bCancel;
	}
	public void setbCancel(int bCancel) {
		this.bCancel = bCancel;
	}
	public String getTrFailDate() {
		return trFailDate;
	}
	public void setTrFailDate(String trFailDate) {
		this.trFailDate = trFailDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeFailDTO [trFailCode=");
		builder.append(trFailCode);
		builder.append(", relatedCode=");
		builder.append(relatedCode);
		builder.append(", trFailType=");
		builder.append(trFailType);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", trFailBasis=");
		builder.append(trFailBasis);
		builder.append(", bCancelRate=");
		builder.append(bCancelRate);
		builder.append(", bCancel=");
		builder.append(bCancel);
		builder.append(", trFailDate=");
		builder.append(trFailDate);
		builder.append(", announcedCode=");
		builder.append(announcedCode);
		builder.append("]");
		return builder.toString();
	}

}
