package com.cafe24.kangk0269.dto;

public class TradePaymentInDTO {
	private String trPayinCode;
	private String trPrCode;
	private String bCode;
	private String announcedCode;
	private String mIdSeller;
	private String mIdBuyer;	
	private int bPrice;
	private String sDepositRate;
	private int bDeposit;
	private int trPayinPrice;	
	private double sCommissionRate;
	private int cTradeCm;	
	private String trPayinDate1;	
	private String trPayinDate2;	
	private String trPayinCheck;
	private String bMoCode;	
	private String bMoDate;	
	private String trPayinStatus;
	public String getTrPayinCode() {
		return trPayinCode;
	}
	public void setTrPayinCode(String trPayinCode) {
		this.trPayinCode = trPayinCode;
	}
	public String getTrPrCode() {
		return trPrCode;
	}
	public void setTrPrCode(String trPrCode) {
		this.trPrCode = trPrCode;
	}
	public String getbCode() {
		return bCode;
	}
	public void setbCode(String bCode) {
		this.bCode = bCode;
	}
	public String getAnnouncedCode() {
		return announcedCode;
	}
	public void setAnnouncedCode(String announcedCode) {
		this.announcedCode = announcedCode;
	}
	public String getmIdSeller() {
		return mIdSeller;
	}
	public void setmIdSeller(String mIdSeller) {
		this.mIdSeller = mIdSeller;
	}
	public String getmIdBuyer() {
		return mIdBuyer;
	}
	public void setmIdBuyer(String mIdBuyer) {
		this.mIdBuyer = mIdBuyer;
	}
	public int getbPrice() {
		return bPrice;
	}
	public void setbPrice(int bPrice) {
		this.bPrice = bPrice;
	}
	public String getsDepositRate() {
		return sDepositRate;
	}
	public void setsDepositRate(String sDepositRate) {
		this.sDepositRate = sDepositRate;
	}
	public int getbDeposit() {
		return bDeposit;
	}
	public void setbDeposit(int bDeposit) {
		this.bDeposit = bDeposit;
	}
	public int getTrPayinPrice() {
		return trPayinPrice;
	}
	public void setTrPayinPrice(int trPayinPrice) {
		this.trPayinPrice = trPayinPrice;
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
	public String getTrPayinDate1() {
		return trPayinDate1;
	}
	public void setTrPayinDate1(String trPayinDate1) {
		this.trPayinDate1 = trPayinDate1;
	}
	public String getTrPayinDate2() {
		return trPayinDate2;
	}
	public void setTrPayinDate2(String trPayinDate2) {
		this.trPayinDate2 = trPayinDate2;
	}
	public String getTrPayinCheck() {
		return trPayinCheck;
	}
	public void setTrPayinCheck(String trPayinCheck) {
		this.trPayinCheck = trPayinCheck;
	}
	public String getbMoCode() {
		return bMoCode;
	}
	public void setbMoCode(String bMoCode) {
		this.bMoCode = bMoCode;
	}
	public String getbMoDate() {
		return bMoDate;
	}
	public void setbMoDate(String bMoDate) {
		this.bMoDate = bMoDate;
	}
	public String getTrPayinStatus() {
		return trPayinStatus;
	}
	public void setTrPayinStatus(String trPayinStatus) {
		this.trPayinStatus = trPayinStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradePaymentInDTO [trPayinCode=");
		builder.append(trPayinCode);
		builder.append(", trPrCode=");
		builder.append(trPrCode);
		builder.append(", bCode=");
		builder.append(bCode);
		builder.append(", announcedCode=");
		builder.append(announcedCode);
		builder.append(", mIdSeller=");
		builder.append(mIdSeller);
		builder.append(", mIdBuyer=");
		builder.append(mIdBuyer);
		builder.append(", bPrice=");
		builder.append(bPrice);
		builder.append(", sDepositRate=");
		builder.append(sDepositRate);
		builder.append(", bDeposit=");
		builder.append(bDeposit);
		builder.append(", trPayinPrice=");
		builder.append(trPayinPrice);
		builder.append(", sCommissionRate=");
		builder.append(sCommissionRate);
		builder.append(", cTradeCm=");
		builder.append(cTradeCm);
		builder.append(", trPayinDate1=");
		builder.append(trPayinDate1);
		builder.append(", trPayinDate2=");
		builder.append(trPayinDate2);
		builder.append(", trPayinCheck=");
		builder.append(trPayinCheck);
		builder.append(", bMoCode=");
		builder.append(bMoCode);
		builder.append(", bMoDate=");
		builder.append(bMoDate);
		builder.append(", trPayinStatus=");
		builder.append(trPayinStatus);
		builder.append("]");
		return builder.toString();
	}

}
