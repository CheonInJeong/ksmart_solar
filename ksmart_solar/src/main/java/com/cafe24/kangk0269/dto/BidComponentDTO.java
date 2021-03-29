package com.cafe24.kangk0269.dto;

public class BidComponentDTO {
	private String bCpCode;	
	private int num;	
	private String mId;	
	private String bCpTitle;	
	private String cpCode;	
	private String bCpContents;
	private int bCpPrice;	
	private String bCpSpecs;	
	private String cpUsedate;	
	private String bCpDocument;	
	private String bCpStatus;	
	private String bCpDate;	
	private String bCpDateBidding1;	
	private String bCpDateBidding2;	
	private String bCpDateDecision1;	
	private String bCpDateDecision2;	
	private String bCpGroupcode;
	private int bCpReCount;	
	private String bCpConfirm;	
	private String bCpConfirmStatus;
	private String bCpRejectReason;	
	private String bCpCancelReason;	
	private String bCpCancelDate;
	private int acStatusCode;
	private int bCpBidderNumber;
	private BidListDTO bidListDTO;
	private ComponentDTO componentDTO;
	public String getbCpCode() {
		return bCpCode;
	}
	public void setbCpCode(String bCpCode) {
		this.bCpCode = bCpCode;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getbCpTitle() {
		return bCpTitle;
	}
	public void setbCpTitle(String bCpTitle) {
		this.bCpTitle = bCpTitle;
	}
	public String getCpCode() {
		return cpCode;
	}
	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}
	public String getbCpContents() {
		return bCpContents;
	}
	public void setbCpContents(String bCpContents) {
		this.bCpContents = bCpContents;
	}
	public int getbCpPrice() {
		return bCpPrice;
	}
	public void setbCpPrice(int bCpPrice) {
		this.bCpPrice = bCpPrice;
	}
	public String getbCpSpecs() {
		return bCpSpecs;
	}
	public void setbCpSpecs(String bCpSpecs) {
		this.bCpSpecs = bCpSpecs;
	}
	public String getCpUsedate() {
		return cpUsedate;
	}
	public void setCpUsedate(String cpUsedate) {
		this.cpUsedate = cpUsedate;
	}
	public String getbCpDocument() {
		return bCpDocument;
	}
	public void setbCpDocument(String bCpDocument) {
		this.bCpDocument = bCpDocument;
	}
	public String getbCpStatus() {
		return bCpStatus;
	}
	public void setbCpStatus(String bCpStatus) {
		this.bCpStatus = bCpStatus;
	}
	public String getbCpDate() {
		return bCpDate;
	}
	public void setbCpDate(String bCpDate) {
		this.bCpDate = bCpDate;
	}
	public String getbCpDateBidding1() {
		return bCpDateBidding1;
	}
	public void setbCpDateBidding1(String bCpDateBidding1) {
		this.bCpDateBidding1 = bCpDateBidding1;
	}
	public String getbCpDateBidding2() {
		return bCpDateBidding2;
	}
	public void setbCpDateBidding2(String bCpDateBidding2) {
		this.bCpDateBidding2 = bCpDateBidding2;
	}
	public String getbCpDateDecision1() {
		return bCpDateDecision1;
	}
	public void setbCpDateDecision1(String bCpDateDecision1) {
		this.bCpDateDecision1 = bCpDateDecision1;
	}
	public String getbCpDateDecision2() {
		return bCpDateDecision2;
	}
	public void setbCpDateDecision2(String bCpDateDecision2) {
		this.bCpDateDecision2 = bCpDateDecision2;
	}
	public String getbCpGroupcode() {
		return bCpGroupcode;
	}
	public void setbCpGroupcode(String bCpGroupcode) {
		this.bCpGroupcode = bCpGroupcode;
	}
	public int getbCpReCount() {
		return bCpReCount;
	}
	public void setbCpReCount(int bCpReCount) {
		this.bCpReCount = bCpReCount;
	}
	public String getbCpConfirm() {
		return bCpConfirm;
	}
	public void setbCpConfirm(String bCpConfirm) {
		this.bCpConfirm = bCpConfirm;
	}
	public String getbCpConfirmStatus() {
		return bCpConfirmStatus;
	}
	public void setbCpConfirmStatus(String bCpConfirmStatus) {
		this.bCpConfirmStatus = bCpConfirmStatus;
	}
	public String getbCpRejectReason() {
		return bCpRejectReason;
	}
	public void setbCpRejectReason(String bCpRejectReason) {
		this.bCpRejectReason = bCpRejectReason;
	}
	public String getbCpCancelReason() {
		return bCpCancelReason;
	}
	public void setbCpCancelReason(String bCpCancelReason) {
		this.bCpCancelReason = bCpCancelReason;
	}
	public String getbCpCancelDate() {
		return bCpCancelDate;
	}
	public void setbCpCancelDate(String bCpCancelDate) {
		this.bCpCancelDate = bCpCancelDate;
	}
	public int getAcStatusCode() {
		return acStatusCode;
	}
	public void setAcStatusCode(int acStatusCode) {
		this.acStatusCode = acStatusCode;
	}
	public int getbCpBidderNumber() {
		return bCpBidderNumber;
	}
	public void setbCpBidderNumber(int bCpBidderNumber) {
		this.bCpBidderNumber = bCpBidderNumber;
	}
	public BidListDTO getBidListDTO() {
		return bidListDTO;
	}
	public void setBidListDTO(BidListDTO bidListDTO) {
		this.bidListDTO = bidListDTO;
	}
	public ComponentDTO getComponentDTO() {
		return componentDTO;
	}
	public void setComponentDTO(ComponentDTO componentDTO) {
		this.componentDTO = componentDTO;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidComponentDTO [bCpCode=");
		builder.append(bCpCode);
		builder.append(", num=");
		builder.append(num);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bCpTitle=");
		builder.append(bCpTitle);
		builder.append(", cpCode=");
		builder.append(cpCode);
		builder.append(", bCpContents=");
		builder.append(bCpContents);
		builder.append(", bCpPrice=");
		builder.append(bCpPrice);
		builder.append(", bCpSpecs=");
		builder.append(bCpSpecs);
		builder.append(", cpUsedate=");
		builder.append(cpUsedate);
		builder.append(", bCpDocument=");
		builder.append(bCpDocument);
		builder.append(", bCpStatus=");
		builder.append(bCpStatus);
		builder.append(", bCpDate=");
		builder.append(bCpDate);
		builder.append(", bCpDateBidding1=");
		builder.append(bCpDateBidding1);
		builder.append(", bCpDateBidding2=");
		builder.append(bCpDateBidding2);
		builder.append(", bCpDateDecision1=");
		builder.append(bCpDateDecision1);
		builder.append(", bCpDateDecision2=");
		builder.append(bCpDateDecision2);
		builder.append(", bCpGroupcode=");
		builder.append(bCpGroupcode);
		builder.append(", bCpReCount=");
		builder.append(bCpReCount);
		builder.append(", bCpConfirm=");
		builder.append(bCpConfirm);
		builder.append(", bCpConfirmStatus=");
		builder.append(bCpConfirmStatus);
		builder.append(", bCpRejectReason=");
		builder.append(bCpRejectReason);
		builder.append(", bCpCancelReason=");
		builder.append(bCpCancelReason);
		builder.append(", bCpCancelDate=");
		builder.append(bCpCancelDate);
		builder.append(", acStatusCode=");
		builder.append(acStatusCode);
		builder.append(", bCpBidderNumber=");
		builder.append(bCpBidderNumber);
		builder.append(", bidListDTO=");
		builder.append(bidListDTO);
		builder.append(", componentDTO=");
		builder.append(componentDTO);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}