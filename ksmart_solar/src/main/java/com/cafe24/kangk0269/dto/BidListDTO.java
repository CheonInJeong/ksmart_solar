package com.cafe24.kangk0269.dto;

public class BidListDTO {
	private String bCode;
	private String announcedCode;
	private String bTypeCode;	
	private String mId;
	private int bPrice;
	private double sDepositRate;
	private int bDeposit;	
	private String bDepositCheck;
	private String bMoCode;	
	private String bDepositDate;
	private String trTypeCode;
	private String trTypeName;	
	private String dcInstructions;	
	private String dcApplication;	
	private String dcProposal;	
	private String bDate;
	private String bCheck;	
	private int bRank;	
	private String bDepositAvailable;
	private String bDepositRefund;
	private String bDateUp;
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
	public String getbTypeCode() {
		return bTypeCode;
	}
	public void setbTypeCode(String bTypeCode) {
		this.bTypeCode = bTypeCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getbPrice() {
		return bPrice;
	}
	public void setbPrice(int bPrice) {
		this.bPrice = bPrice;
	}
	public double getsDepositRate() {
		return sDepositRate;
	}
	public void setsDepositRate(double sDepositRate) {
		this.sDepositRate = sDepositRate;
	}
	public int getbDeposit() {
		return bDeposit;
	}
	public void setbDeposit(int bDeposit) {
		this.bDeposit = bDeposit;
	}
	public String getbDepositCheck() {
		return bDepositCheck;
	}
	public void setbDepositCheck(String bDepositCheck) {
		this.bDepositCheck = bDepositCheck;
	}
	public String getbMoCode() {
		return bMoCode;
	}
	public void setbMoCode(String bMoCode) {
		this.bMoCode = bMoCode;
	}
	public String getbDepositDate() {
		return bDepositDate;
	}
	public void setbDepositDate(String bDepositDate) {
		this.bDepositDate = bDepositDate;
	}
	public String getTrTypeCode() {
		return trTypeCode;
	}
	public void setTrTypeCode(String trTypeCode) {
		this.trTypeCode = trTypeCode;
	}
	public String getTrTypeName() {
		return trTypeName;
	}
	public void setTrTypeName(String trTypeName) {
		this.trTypeName = trTypeName;
	}
	public String getDcInstructions() {
		return dcInstructions;
	}
	public void setDcInstructions(String dcInstructions) {
		this.dcInstructions = dcInstructions;
	}
	public String getDcApplication() {
		return dcApplication;
	}
	public void setDcApplication(String dcApplication) {
		this.dcApplication = dcApplication;
	}
	public String getDcProposal() {
		return dcProposal;
	}
	public void setDcProposal(String dcProposal) {
		this.dcProposal = dcProposal;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public String getbCheck() {
		return bCheck;
	}
	public void setbCheck(String bCheck) {
		this.bCheck = bCheck;
	}
	public int getbRank() {
		return bRank;
	}
	public void setbRank(int bRank) {
		this.bRank = bRank;
	}
	public String getbDepositAvailable() {
		return bDepositAvailable;
	}
	public void setbDepositAvailable(String bDepositAvailable) {
		this.bDepositAvailable = bDepositAvailable;
	}
	public String getbDepositRefund() {
		return bDepositRefund;
	}
	public void setbDepositRefund(String bDepositRefund) {
		this.bDepositRefund = bDepositRefund;
	}
	public String getbDateUp() {
		return bDateUp;
	}
	public void setbDateUp(String bDateUp) {
		this.bDateUp = bDateUp;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidListDTO [bCode=");
		builder.append(bCode);
		builder.append(", announcedCode=");
		builder.append(announcedCode);
		builder.append(", bTypeCode=");
		builder.append(bTypeCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bPrice=");
		builder.append(bPrice);
		builder.append(", sDepositRate=");
		builder.append(sDepositRate);
		builder.append(", bDeposit=");
		builder.append(bDeposit);
		builder.append(", bDepositCheck=");
		builder.append(bDepositCheck);
		builder.append(", bMoCode=");
		builder.append(bMoCode);
		builder.append(", bDepositDate=");
		builder.append(bDepositDate);
		builder.append(", trTypeCode=");
		builder.append(trTypeCode);
		builder.append(", trTypeName=");
		builder.append(trTypeName);
		builder.append(", dcInstructions=");
		builder.append(dcInstructions);
		builder.append(", dcApplication=");
		builder.append(dcApplication);
		builder.append(", dcProposal=");
		builder.append(dcProposal);
		builder.append(", bDate=");
		builder.append(bDate);
		builder.append(", bCheck=");
		builder.append(bCheck);
		builder.append(", bRank=");
		builder.append(bRank);
		builder.append(", bDepositAvailable=");
		builder.append(bDepositAvailable);
		builder.append(", bDepositRefund=");
		builder.append(bDepositRefund);
		builder.append(", bDateUp=");
		builder.append(bDateUp);
		builder.append("]");
		return builder.toString();
	}


}
