package com.cafe24.kangk0269.dto;

public class StandardDTO {
	
	private String mId;
	
	private int sCommissionIdx;
	private String sCommissionType;
	private double sCommissionRate;
	private String sCommissionDate;
	private String sCommissionUse;
	private String sCommissionLast;
	
	private int sDepositIdx;
	private double sDepositRate;
	private String sDepositDate;
	private String sDepositUse;
	private String sDepositType;
	private String sDepositLast;
	
	private int sTradeIdx;
	private String sTradeType;
	private int sTradePeriod;
	private String sTradeDate;
	private String sTradeUse;
	private String sTradeLast;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
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
	public double getsCommissionRate() {
		return sCommissionRate;
	}
	public void setsCommissionRate(double sCommissionRate) {
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
	public String getsCommissionLast() {
		return sCommissionLast;
	}
	public void setsCommissionLast(String sCommissionLast) {
		this.sCommissionLast = sCommissionLast;
	}
	public int getsDepositIdx() {
		return sDepositIdx;
	}
	public void setsDepositIdx(int sDepositIdx) {
		this.sDepositIdx = sDepositIdx;
	}
	public double getsDepositRate() {
		return sDepositRate;
	}
	public void setsDepositRate(double sDepositRate) {
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
	public String getsDepositType() {
		return sDepositType;
	}
	public void setsDepositType(String sDepositType) {
		this.sDepositType = sDepositType;
	}
	public String getsDepositLast() {
		return sDepositLast;
	}
	public void setsDepositLast(String sDepositLast) {
		this.sDepositLast = sDepositLast;
	}
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
	public String getsTradeLast() {
		return sTradeLast;
	}
	public void setsTradeLast(String sTradeLast) {
		this.sTradeLast = sTradeLast;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StandardDTO [mId=");
		builder.append(mId);
		builder.append(", sCommissionIdx=");
		builder.append(sCommissionIdx);
		builder.append(", sCommissionType=");
		builder.append(sCommissionType);
		builder.append(", sCommissionRate=");
		builder.append(sCommissionRate);
		builder.append(", sCommissionDate=");
		builder.append(sCommissionDate);
		builder.append(", sCommissionUse=");
		builder.append(sCommissionUse);
		builder.append(", sCommissionLast=");
		builder.append(sCommissionLast);
		builder.append(", sDepositIdx=");
		builder.append(sDepositIdx);
		builder.append(", sDepositRate=");
		builder.append(sDepositRate);
		builder.append(", sDepositDate=");
		builder.append(sDepositDate);
		builder.append(", sDepositUse=");
		builder.append(sDepositUse);
		builder.append(", sDepositType=");
		builder.append(sDepositType);
		builder.append(", sDepositLast=");
		builder.append(sDepositLast);
		builder.append(", sTradeIdx=");
		builder.append(sTradeIdx);
		builder.append(", sTradeType=");
		builder.append(sTradeType);
		builder.append(", sTradePeriod=");
		builder.append(sTradePeriod);
		builder.append(", sTradeDate=");
		builder.append(sTradeDate);
		builder.append(", sTradeUse=");
		builder.append(sTradeUse);
		builder.append(", sTradeLast=");
		builder.append(sTradeLast);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
