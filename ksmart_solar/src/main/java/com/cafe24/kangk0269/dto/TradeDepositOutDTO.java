package com.cafe24.kangk0269.dto;

public class TradeDepositOutDTO {
	private String trDepCode;
	private String bCode;
	private String mId;
	private int bDeposit;
	private int trDepDeposit;
	private String mAccountBankName;
	private String mAccountNumber;
	private String trDepAccountCheck;
	private String mAccountName;
	private String trDepDate;
	private String trDepCheck;
	private String trDepWdDate;
	private String bMoCode;
	// 예치금 출금신청 관련공고
	private String announcedCode;
	private String bTitle;
	private String bTypeCode;
	
	public String getTrDepCode() {
		return trDepCode;
	}
	public void setTrDepCode(String trDepCode) {
		this.trDepCode = trDepCode;
	}
	public String getbCode() {
		return bCode;
	}
	public void setbCode(String bCode) {
		this.bCode = bCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getbDeposit() {
		return bDeposit;
	}
	public void setbDeposit(int bDeposit) {
		this.bDeposit = bDeposit;
	}
	public int getTrDepDeposit() {
		return trDepDeposit;
	}
	public void setTrDepDeposit(int trDepDeposit) {
		this.trDepDeposit = trDepDeposit;
	}
	public String getmAccountBankName() {
		return mAccountBankName;
	}
	public void setmAccountBankName(String mAccountBankName) {
		this.mAccountBankName = mAccountBankName;
	}
	public String getmAccountNumber() {
		return mAccountNumber;
	}
	public void setmAccountNumber(String mAccountNumber) {
		this.mAccountNumber = mAccountNumber;
	}
	public String getTrDepAccountCheck() {
		return trDepAccountCheck;
	}
	public void setTrDepAccountCheck(String trDepAccountCheck) {
		this.trDepAccountCheck = trDepAccountCheck;
	}
	public String getmAccountName() {
		return mAccountName;
	}
	public void setmAccountName(String mAccountName) {
		this.mAccountName = mAccountName;
	}
	public String getTrDepDate() {
		return trDepDate;
	}
	public void setTrDepDate(String trDepDate) {
		this.trDepDate = trDepDate;
	}
	public String getTrDepCheck() {
		return trDepCheck;
	}
	public void setTrDepCheck(String trDepCheck) {
		this.trDepCheck = trDepCheck;
	}
	public String getTrDepWdDate() {
		return trDepWdDate;
	}
	public void setTrDepWdDate(String trDepWdDate) {
		this.trDepWdDate = trDepWdDate;
	}
	public String getbMoCode() {
		return bMoCode;
	}
	public void setbMoCode(String bMoCode) {
		this.bMoCode = bMoCode;
	}
	public String getAnnouncedCode() {
		return announcedCode;
	}
	public void setAnnouncedCode(String announcedCode) {
		this.announcedCode = announcedCode;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbTypeCode() {
		return bTypeCode;
	}
	public void setbTypeCode(String bTypeCode) {
		this.bTypeCode = bTypeCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeDepositOutDTO [trDepCode=");
		builder.append(trDepCode);
		builder.append(", bCode=");
		builder.append(bCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bDeposit=");
		builder.append(bDeposit);
		builder.append(", trDepDeposit=");
		builder.append(trDepDeposit);
		builder.append(", mAccountBankName=");
		builder.append(mAccountBankName);
		builder.append(", mAccountNumber=");
		builder.append(mAccountNumber);
		builder.append(", trDepAccountCheck=");
		builder.append(trDepAccountCheck);
		builder.append(", mAccountName=");
		builder.append(mAccountName);
		builder.append(", trDepDate=");
		builder.append(trDepDate);
		builder.append(", trDepCheck=");
		builder.append(trDepCheck);
		builder.append(", trDepWdDate=");
		builder.append(trDepWdDate);
		builder.append(", bMoCode=");
		builder.append(bMoCode);
		builder.append(", announcedCode=");
		builder.append(announcedCode);
		builder.append(", bTitle=");
		builder.append(bTitle);
		builder.append(", bTypeCode=");
		builder.append(bTypeCode);
		builder.append("]");
		return builder.toString();
	}

	
}
