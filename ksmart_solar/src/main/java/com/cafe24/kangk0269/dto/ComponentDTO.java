package com.cafe24.kangk0269.dto;

public class ComponentDTO {
	private String cpCode;
	private String mId;
	private String cpCame;	
	private String cpInfo;	
	private String cpMaker;	
	private String cpMakedate;	
	private String cpUsedate;	
	private String cpDate;
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
	public String getCpCame() {
		return cpCame;
	}
	public void setCpCame(String cpCame) {
		this.cpCame = cpCame;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComponentDTO [cpCode=");
		builder.append(cpCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", cpCame=");
		builder.append(cpCame);
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
		builder.append("]");
		return builder.toString();
	}
	
}
