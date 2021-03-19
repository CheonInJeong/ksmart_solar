package com.cafe24.kangk0269.dto;

public class BidStatusDTO {
	private String bStatusCode;
	private String bStatusName;
	private String bStautsDate;
	private String bStatusDetail;
	public String getbStatusCode() {
		return bStatusCode;
	}
	public void setbStatusCode(String bStatusCode) {
		this.bStatusCode = bStatusCode;
	}
	public String getbStatusName() {
		return bStatusName;
	}
	public void setbStatusName(String bStatusName) {
		this.bStatusName = bStatusName;
	}
	public String getbStautsDate() {
		return bStautsDate;
	}
	public void setbStautsDate(String bStautsDate) {
		this.bStautsDate = bStautsDate;
	}
	public String getbStatusDetail() {
		return bStatusDetail;
	}
	public void setbStatusDetail(String bStatusDetail) {
		this.bStatusDetail = bStatusDetail;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidStatusDTO [bStatusCode=");
		builder.append(bStatusCode);
		builder.append(", bStatusName=");
		builder.append(bStatusName);
		builder.append(", bStautsDate=");
		builder.append(bStautsDate);
		builder.append(", bStatusDetail=");
		builder.append(bStatusDetail);
		builder.append("]");
		return builder.toString();
	}

}
