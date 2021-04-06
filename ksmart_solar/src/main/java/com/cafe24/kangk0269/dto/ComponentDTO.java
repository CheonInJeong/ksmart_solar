package com.cafe24.kangk0269.dto;

public class ComponentDTO {
	private String cpCode;
	private String mId;
	private String cpName;	
	private String cpPhoto;	
	private String cpInfo;	
	private String cpMaker;	
	private String cpMakedate;	
	private String cpUsedate;	
	private String cpDate;
	private BidComponentDTO bidComponentDTO;
	public String getCpCode() {
		return cpCode;
	}
	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public String getCpInfo() {
		return cpInfo;
	}
	public void setCpInfo(String cpInfo) {
		this.cpInfo = cpInfo;
	}
	public String getCpMaker() {
		return cpMaker;
	}
	public void setCpMaker(String cpMaker) {
		this.cpMaker = cpMaker;
	}
	public String getCpMakedate() {
		return cpMakedate;
	}
	public void setCpMakedate(String cpMakedate) {
		this.cpMakedate = cpMakedate;
	}
	public String getCpUsedate() {
		return cpUsedate;
	}
	public void setCpUsedate(String cpUsedate) {
		this.cpUsedate = cpUsedate;
	}
	public String getCpDate() {
		return cpDate;
	}
	public void setCpDate(String cpDate) {
		this.cpDate = cpDate;
	}
	public BidComponentDTO getBidComponentDTO() {
		return bidComponentDTO;
	}
	public void setBidComponentDTO(BidComponentDTO bidComponentDTO) {
		this.bidComponentDTO = bidComponentDTO;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComponentDTO [cpCode=");
		builder.append(cpCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", cpName=");
		builder.append(cpName);
		builder.append(", cpInfo=");
		builder.append(cpInfo);
		builder.append(", cpMaker=");
		builder.append(cpMaker);
		builder.append(", cpMakedate=");
		builder.append(cpMakedate);
		builder.append(", cpUsedate=");
		builder.append(cpUsedate);
		builder.append(", cpDate=");
		builder.append(cpDate);
		builder.append(", bidComponentDTO=");
		builder.append(bidComponentDTO);
		builder.append("]");
		return builder.toString();
	}
	public String getCpPhoto() {
		return cpPhoto;
	}
	public void setCpPhoto(String cpPhoto) {
		this.cpPhoto = cpPhoto;
	}
	
	
	
}
