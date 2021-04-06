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
	// 관련 공고코드
	private String announcedCode;
	// 관련 공고제목
	private String announcedTitle;
	// 관련 공고품목유형
	private String bType;
	// 납부 은행
	private String bankName;
	// 납부 계좌
	private String accountNumber;
	// 납부자명
	private String paymentName;
	
	// 취소수수료 총액
	private int failTotal;
	// 거래수수료 총액
	private int sucTotal;
	// 수수료 총액
	private int cmTotal;
	// 일자(년월)
	private String ymDate;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getAnnouncedTitle() {
		return announcedTitle;
	}
	public void setAnnouncedTitle(String announcedTitle) {
		this.announcedTitle = announcedTitle;
	}
	public String getbType() {
		return bType;
	}
	public void setbType(String bType) {
		this.bType = bType;
	}
	public int getFailTotal() {
		return failTotal;
	}
	public void setFailTotal(int failTotal) {
		this.failTotal = failTotal;
	}
	public int getSucTotal() {
		return sucTotal;
	}
	public void setSucTotal(int sucTotal) {
		this.sucTotal = sucTotal;
	}
	public int getCmTotal() {
		return cmTotal;
	}
	public void setCmTotal(int cmTotal) {
		this.cmTotal = cmTotal;
	}
	public String getYmDate() {
		return ymDate;
	}
	public void setYmDate(String ymDate) {
		this.ymDate = ymDate;
	}
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
		builder.append(", announcedTitle=");
		builder.append(announcedTitle);
		builder.append(", bType=");
		builder.append(bType);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", paymentName=");
		builder.append(paymentName);
		builder.append(", failTotal=");
		builder.append(failTotal);
		builder.append(", sucTotal=");
		builder.append(sucTotal);
		builder.append(", cmTotal=");
		builder.append(cmTotal);
		builder.append(", ymDate=");
		builder.append(ymDate);
		builder.append("]");
		return builder.toString();
	}


}
