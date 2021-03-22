package com.cafe24.kangk0269.dto;

public class TradePaymentOutDTO {
	private String trPayoutCode;
	private String trPrCode;
	private String mId;
	private int trPayoutPrice;
	private double sCommissionRate;
	private int cTradeCm;
	private int trPayoutPriceReal;
	private String trPayoutBank;
	private String trPayoutAccount;
	private String trPayoutAccountCheck;
	private String trPayoutAccountName;
	private String trPayoutDate;	
	private String trPayoutCheck;	
	private String trPayoutWdDate;
	private String bMoCode;
	public String getTrPayoutCode() {
		return trPayoutCode;
	}
	public void setTrPayoutCode(String trPayoutCode) {
		this.trPayoutCode = trPayoutCode;
	}
	public String getTrPrCode() {
		return trPrCode;
	}
	public void setTrPrCode(String trPrCode) {
		this.trPrCode = trPrCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getTrPayoutPrice() {
		return trPayoutPrice;
	}
	public void setTrPayoutPrice(int trPayoutPrice) {
		this.trPayoutPrice = trPayoutPrice;
	}
	public double getsCommissionRate() {
		return sCommissionRate;
	}
	public void setsCommissionRate(double sCommissionRate) {
		this.sCommissionRate = sCommissionRate;
	}
	public int getcTradeCm() {
		return cTradeCm;
	}
	public void setcTradeCm(int cTradeCm) {
		this.cTradeCm = cTradeCm;
	}
	public int getTrPayoutPriceReal() {
		return trPayoutPriceReal;
	}
	public void setTrPayoutPriceReal(int trPayoutPriceReal) {
		this.trPayoutPriceReal = trPayoutPriceReal;
	}
	public String getTrPayoutBank() {
		return trPayoutBank;
	}
	public void setTrPayoutBank(String trPayoutBank) {
		this.trPayoutBank = trPayoutBank;
	}
	public String getTrPayoutAccount() {
		return trPayoutAccount;
	}
	public void setTrPayoutAccount(String trPayoutAccount) {
		this.trPayoutAccount = trPayoutAccount;
	}
	public String getTrPayoutAccountCheck() {
		return trPayoutAccountCheck;
	}
	public void setTrPayoutAccountCheck(String trPayoutAccountCheck) {
		this.trPayoutAccountCheck = trPayoutAccountCheck;
	}
	public String getTrPayoutAccountName() {
		return trPayoutAccountName;
	}
	public void setTrPayoutAccountName(String trPayoutAccountName) {
		this.trPayoutAccountName = trPayoutAccountName;
	}
	public String getTrPayoutDate() {
		return trPayoutDate;
	}
	public void setTrPayoutDate(String trPayoutDate) {
		this.trPayoutDate = trPayoutDate;
	}
	public String getTrPayoutCheck() {
		return trPayoutCheck;
	}
	public void setTrPayoutCheck(String trPayoutCheck) {
		this.trPayoutCheck = trPayoutCheck;
	}
	public String getTrPayoutWdDate() {
		return trPayoutWdDate;
	}
	public void setTrPayoutWdDate(String trPayoutWdDate) {
		this.trPayoutWdDate = trPayoutWdDate;
	}
	public String getbMoCode() {
		return bMoCode;
	}
	public void setbMoCode(String bMoCode) {
		this.bMoCode = bMoCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradePaymentOutDTO [trPayoutCode=");
		builder.append(trPayoutCode);
		builder.append(", trPrCode=");
		builder.append(trPrCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", trPayoutPrice=");
		builder.append(trPayoutPrice);
		builder.append(", sCommissionRate=");
		builder.append(sCommissionRate);
		builder.append(", cTradeCm=");
		builder.append(cTradeCm);
		builder.append(", trPayoutPriceReal=");
		builder.append(trPayoutPriceReal);
		builder.append(", trPayoutBank=");
		builder.append(trPayoutBank);
		builder.append(", trPayoutAccount=");
		builder.append(trPayoutAccount);
		builder.append(", trPayoutAccountCheck=");
		builder.append(trPayoutAccountCheck);
		builder.append(", trPayoutAccountName=");
		builder.append(trPayoutAccountName);
		builder.append(", trPayoutDate=");
		builder.append(trPayoutDate);
		builder.append(", trPayoutCheck=");
		builder.append(trPayoutCheck);
		builder.append(", trPayoutWdDate=");
		builder.append(trPayoutWdDate);
		builder.append(", bMoCode=");
		builder.append(bMoCode);
		builder.append("]");
		return builder.toString();
	}

}
