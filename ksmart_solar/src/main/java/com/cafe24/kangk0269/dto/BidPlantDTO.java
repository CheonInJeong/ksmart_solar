package com.cafe24.kangk0269.dto;

public class BidPlantDTO {

	private int num;
	private String bPlCode;
	private String mId;
	private String bPlTitle;
	private String bzPlCode;
	private String bPlContents;
	private int bPlPrice;
	private String bPlSpecs;
	private String bPlDocument;
	private int plDepDataResidual;	
	private int plDepPrice;
	private String plDepStartDate;
	private String bPlStatus;
	private String bPlDateRequest;
	private String bPlDateBidding1;
	private String bPlDateBidding2;	
	private String bPlDateDecision1;
	private String bPlDateDecision2;	
	private String bPlGroupcode;
	private int bPlReCount;
	private String bPlConfirm;
	private String bPlConfirmStatus;
	private String bPlRejectReason;	
	private String bPlCancelReason;
	private String bPlCancelDate;
	private int acStatusCode;
	private BidListDTO bidListDTO;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getbPlCode() {
		return bPlCode;
	}
	public void setbPlCode(String bPlCode) {
		this.bPlCode = bPlCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getbPlTitle() {
		return bPlTitle;
	}
	public void setbPlTitle(String bPlTitle) {
		this.bPlTitle = bPlTitle;
	}
	public String getBzPlCode() {
		return bzPlCode;
	}
	public void setBzPlCode(String bzPlCode) {
		this.bzPlCode = bzPlCode;
	}
	public String getbPlContents() {
		return bPlContents;
	}
	public void setbPlContents(String bPlContents) {
		this.bPlContents = bPlContents;
	}
	public int getbPlPrice() {
		return bPlPrice;
	}
	public void setbPlPrice(int bPlPrice) {
		this.bPlPrice = bPlPrice;
	}
	public String getbPlSpecs() {
		return bPlSpecs;
	}
	public void setbPlSpecs(String bPlSpecs) {
		this.bPlSpecs = bPlSpecs;
	}
	public String getbPlDocument() {
		return bPlDocument;
	}
	public void setbPlDocument(String bPlDocument) {
		this.bPlDocument = bPlDocument;
	}
	public int getPlDepDataResidual() {
		return plDepDataResidual;
	}
	public void setPlDepDataResidual(int plDepDataResidual) {
		this.plDepDataResidual = plDepDataResidual;
	}
	public int getPlDepPrice() {
		return plDepPrice;
	}
	public void setPlDepPrice(int plDepPrice) {
		this.plDepPrice = plDepPrice;
	}
	public String getPlDepStartDate() {
		return plDepStartDate;
	}
	public void setPlDepStartDate(String plDepStartDate) {
		this.plDepStartDate = plDepStartDate;
	}
	public String getbPlStatus() {
		return bPlStatus;
	}
	public void setbPlStatus(String bPlStatus) {
		this.bPlStatus = bPlStatus;
	}
	public String getbPlDateRequest() {
		return bPlDateRequest;
	}
	public void setbPlDateRequest(String bPlDateRequest) {
		this.bPlDateRequest = bPlDateRequest;
	}
	public String getbPlDateBidding1() {
		return bPlDateBidding1;
	}
	public void setbPlDateBidding1(String bPlDateBidding1) {
		this.bPlDateBidding1 = bPlDateBidding1;
	}
	public String getbPlDateBidding2() {
		return bPlDateBidding2;
	}
	public void setbPlDateBidding2(String bPlDateBidding2) {
		this.bPlDateBidding2 = bPlDateBidding2;
	}
	public String getbPlDateDecision1() {
		return bPlDateDecision1;
	}
	public void setbPlDateDecision1(String bPlDateDecision1) {
		this.bPlDateDecision1 = bPlDateDecision1;
	}
	public String getbPlDateDecision2() {
		return bPlDateDecision2;
	}
	public void setbPlDateDecision2(String bPlDateDecision2) {
		this.bPlDateDecision2 = bPlDateDecision2;
	}
	public String getbPlGroupcode() {
		return bPlGroupcode;
	}
	public void setbPlGroupcode(String bPlGroupcode) {
		this.bPlGroupcode = bPlGroupcode;
	}
	public int getbPlReCount() {
		return bPlReCount;
	}
	public void setbPlReCount(int bPlReCount) {
		this.bPlReCount = bPlReCount;
	}
	public String getbPlConfirm() {
		return bPlConfirm;
	}
	public void setbPlConfirm(String bPlConfirm) {
		this.bPlConfirm = bPlConfirm;
	}
	public String getbPlConfirmStatus() {
		return bPlConfirmStatus;
	}
	public void setbPlConfirmStatus(String bPlConfirmStatus) {
		this.bPlConfirmStatus = bPlConfirmStatus;
	}
	public String getbPlRejectReason() {
		return bPlRejectReason;
	}
	public void setbPlRejectReason(String bPlRejectReason) {
		this.bPlRejectReason = bPlRejectReason;
	}
	public String getbPlCancelReason() {
		return bPlCancelReason;
	}
	public void setbPlCancelReason(String bPlCancelReason) {
		this.bPlCancelReason = bPlCancelReason;
	}
	public String getbPlCancelDate() {
		return bPlCancelDate;
	}
	public void setbPlCancelDate(String bPlCancelDate) {
		this.bPlCancelDate = bPlCancelDate;
	}
	public int getAcStatusCode() {
		return acStatusCode;
	}
	public void setAcStatusCode(int acStatusCode) {
		this.acStatusCode = acStatusCode;
	}
	public BidListDTO getBidListDTO() {
		return bidListDTO;
	}
	public void setBidListDTO(BidListDTO bidListDTO) {
		this.bidListDTO = bidListDTO;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidPlantDTO [num=");
		builder.append(num);
		builder.append(", bPlCode=");
		builder.append(bPlCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bPlTitle=");
		builder.append(bPlTitle);
		builder.append(", bzPlCode=");
		builder.append(bzPlCode);
		builder.append(", bPlContents=");
		builder.append(bPlContents);
		builder.append(", bPlPrice=");
		builder.append(bPlPrice);
		builder.append(", bPlSpecs=");
		builder.append(bPlSpecs);
		builder.append(", bPlDocument=");
		builder.append(bPlDocument);
		builder.append(", plDepDataResidual=");
		builder.append(plDepDataResidual);
		builder.append(", plDepPrice=");
		builder.append(plDepPrice);
		builder.append(", plDepStartDate=");
		builder.append(plDepStartDate);
		builder.append(", bPlStatus=");
		builder.append(bPlStatus);
		builder.append(", bPlDateRequest=");
		builder.append(bPlDateRequest);
		builder.append(", bPlDateBidding1=");
		builder.append(bPlDateBidding1);
		builder.append(", bPlDateBidding2=");
		builder.append(bPlDateBidding2);
		builder.append(", bPlDateDecision1=");
		builder.append(bPlDateDecision1);
		builder.append(", bPlDateDecision2=");
		builder.append(bPlDateDecision2);
		builder.append(", bPlGroupcode=");
		builder.append(bPlGroupcode);
		builder.append(", bPlReCount=");
		builder.append(bPlReCount);
		builder.append(", bPlConfirm=");
		builder.append(bPlConfirm);
		builder.append(", bPlConfirmStatus=");
		builder.append(bPlConfirmStatus);
		builder.append(", bPlRejectReason=");
		builder.append(bPlRejectReason);
		builder.append(", bPlCancelReason=");
		builder.append(bPlCancelReason);
		builder.append(", bPlCancelDate=");
		builder.append(bPlCancelDate);
		builder.append(", acStatusCode=");
		builder.append(acStatusCode);
		builder.append(", bidListDTO=");
		builder.append(bidListDTO);
		builder.append("]");
		return builder.toString();
	}
	
	
}
