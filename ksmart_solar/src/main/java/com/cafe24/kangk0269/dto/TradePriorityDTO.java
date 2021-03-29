package com.cafe24.kangk0269.dto;

public class TradePriorityDTO {
	private String trPrCode;
	private String bCode;
	private String bTypeCode;
	private String announcedCode;
	private String announcedTitle;
	private String bGroupcode;	
	private String mIdSeller;
	private String mIdBuyer;
	private int trPrRank;
	private int trPrPrice;
	private double sDepositRate;
	private int bDeposit;
	private String trPrConclusionDate1;
	private String trPrConclusionDate2;
	private String trTypeCode;
	private String trTypeName;
	private String trPrDateUp;
	private String trPayoutCheck;
	
	private double comissionRate;
	private int commission;
	private int availableOut;
	
	private TradePaymentInDTO tradePaymentInDTO;

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

	public String getbTypeCode() {
		return bTypeCode;
	}

	public void setbTypeCode(String bTypeCode) {
		this.bTypeCode = bTypeCode;
	}

	public String getAnnouncedCode() {
		return announcedCode;
	}

	public void setAnnouncedCode(String announcedCode) {
		this.announcedCode = announcedCode;
	}

	public String getAnnouncedTitle() {
		return announcedTitle;
	}

	public void setAnnouncedTitle(String announcedTitle) {
		this.announcedTitle = announcedTitle;
	}

	public String getbGroupcode() {
		return bGroupcode;
	}

	public void setbGroupcode(String bGroupcode) {
		this.bGroupcode = bGroupcode;
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

	public int getTrPrRank() {
		return trPrRank;
	}

	public void setTrPrRank(int trPrRank) {
		this.trPrRank = trPrRank;
	}

	public int getTrPrPrice() {
		return trPrPrice;
	}

	public void setTrPrPrice(int trPrPrice) {
		this.trPrPrice = trPrPrice;
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

	public String getTrPrConclusionDate1() {
		return trPrConclusionDate1;
	}

	public void setTrPrConclusionDate1(String trPrConclusionDate1) {
		this.trPrConclusionDate1 = trPrConclusionDate1;
	}

	public String getTrPrConclusionDate2() {
		return trPrConclusionDate2;
	}

	public void setTrPrConclusionDate2(String trPrConclusionDate2) {
		this.trPrConclusionDate2 = trPrConclusionDate2;
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

	public String getTrPrDateUp() {
		return trPrDateUp;
	}

	public void setTrPrDateUp(String trPrDateUp) {
		this.trPrDateUp = trPrDateUp;
	}

	public String getTrPayoutCheck() {
		return trPayoutCheck;
	}

	public void setTrPayoutCheck(String trPayoutCheck) {
		this.trPayoutCheck = trPayoutCheck;
	}

	public double getComissionRate() {
		return comissionRate;
	}

	public void setComissionRate(double comissionRate) {
		this.comissionRate = comissionRate;
	}

	public int getCommission() {
		return commission;
	}

	public void setCommission(int commission) {
		this.commission = commission;
	}

	public int getAvailableOut() {
		return availableOut;
	}

	public void setAvailableOut(int availableOut) {
		this.availableOut = availableOut;
	}

	public TradePaymentInDTO getTradePaymentInDTO() {
		return tradePaymentInDTO;
	}

	public void setTradePaymentInDTO(TradePaymentInDTO tradePaymentInDTO) {
		this.tradePaymentInDTO = tradePaymentInDTO;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradePriorityDTO [trPrCode=");
		builder.append(trPrCode);
		builder.append(", bCode=");
		builder.append(bCode);
		builder.append(", bTypeCode=");
		builder.append(bTypeCode);
		builder.append(", announcedCode=");
		builder.append(announcedCode);
		builder.append(", announcedTitle=");
		builder.append(announcedTitle);
		builder.append(", bGroupcode=");
		builder.append(bGroupcode);
		builder.append(", mIdSeller=");
		builder.append(mIdSeller);
		builder.append(", mIdBuyer=");
		builder.append(mIdBuyer);
		builder.append(", trPrRank=");
		builder.append(trPrRank);
		builder.append(", trPrPrice=");
		builder.append(trPrPrice);
		builder.append(", sDepositRate=");
		builder.append(sDepositRate);
		builder.append(", bDeposit=");
		builder.append(bDeposit);
		builder.append(", trPrConclusionDate1=");
		builder.append(trPrConclusionDate1);
		builder.append(", trPrConclusionDate2=");
		builder.append(trPrConclusionDate2);
		builder.append(", trTypeCode=");
		builder.append(trTypeCode);
		builder.append(", trTypeName=");
		builder.append(trTypeName);
		builder.append(", trPrDateUp=");
		builder.append(trPrDateUp);
		builder.append(", trPayoutCheck=");
		builder.append(trPayoutCheck);
		builder.append(", comissionRate=");
		builder.append(comissionRate);
		builder.append(", commission=");
		builder.append(commission);
		builder.append(", availableOut=");
		builder.append(availableOut);
		builder.append(", tradePaymentInDTO=");
		builder.append(tradePaymentInDTO);
		builder.append("]");
		return builder.toString();
	}
	

	
}


