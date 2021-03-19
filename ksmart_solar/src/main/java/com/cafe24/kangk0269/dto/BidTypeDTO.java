package com.cafe24.kangk0269.dto;

public class BidTypeDTO {
	private String bTypeCode;
	private String bTypeName;
	private String bTypeDate;
	public String getbTypeCode() {
		return bTypeCode;
	}
	public void setbTypeCode(String bTypeCode) {
		this.bTypeCode = bTypeCode;
	}
	public String getbTypeName() {
		return bTypeName;
	}
	public void setbTypeName(String bTypeName) {
		this.bTypeName = bTypeName;
	}
	public String getbTypeDate() {
		return bTypeDate;
	}
	public void setbTypeDate(String bTypeDate) {
		this.bTypeDate = bTypeDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidTypeDTO [bTypeCode=");
		builder.append(bTypeCode);
		builder.append(", bTypeName=");
		builder.append(bTypeName);
		builder.append(", bTypeDate=");
		builder.append(bTypeDate);
		builder.append("]");
		return builder.toString();
	}

}
