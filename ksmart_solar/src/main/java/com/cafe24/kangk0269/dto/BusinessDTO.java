package com.cafe24.kangk0269.dto;

public class BusinessDTO {
	private String bzCode;
	private String mId;
	private String bzCompanyName;
	private String bzCeoName;
	private String bzZipcode;
	private String bzAddr;
	private String bzDetailAddr;
	private String bzPlace;
	private String bzLicense;
	private String bzType;
	private String bzRequestDate;
	private String bzCheck;
	private String bzCheckReason;
	public String getBzCode() {
		return bzCode;
	}
	public void setBzCode(String bzCode) {
		this.bzCode = bzCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getBzCompanyName() {
		return bzCompanyName;
	}
	public void setBzCompanyName(String bzCompanyName) {
		this.bzCompanyName = bzCompanyName;
	}
	public String getBzCeoName() {
		return bzCeoName;
	}
	public void setBzCeoName(String bzCeoName) {
		this.bzCeoName = bzCeoName;
	}
	public String getBzZipcode() {
		return bzZipcode;
	}
	public void setBzZipcode(String bzZipcode) {
		this.bzZipcode = bzZipcode;
	}
	public String getBzAddr() {
		return bzAddr;
	}
	public void setBzAddr(String bzAddr) {
		this.bzAddr = bzAddr;
	}
	public String getBzDetailAddr() {
		return bzDetailAddr;
	}
	public void setBzDetailAddr(String bzDetailAddr) {
		this.bzDetailAddr = bzDetailAddr;
	}
	public String getBzPlace() {
		return bzPlace;
	}
	public void setBzPlace(String bzPlace) {
		this.bzPlace = bzPlace;
	}
	public String getBzLicense() {
		return bzLicense;
	}
	public void setBzLicense(String bzLicense) {
		this.bzLicense = bzLicense;
	}
	public String getBzType() {
		return bzType;
	}
	public void setBzType(String bzType) {
		this.bzType = bzType;
	}
	public String getBzRequestDate() {
		return bzRequestDate;
	}
	public void setBzRequestDate(String bzRequestDate) {
		this.bzRequestDate = bzRequestDate;
	}
	public String getBzCheck() {
		return bzCheck;
	}
	public void setBzCheck(String bzCheck) {
		this.bzCheck = bzCheck;
	}
	public String getBzCheckReason() {
		return bzCheckReason;
	}
	public void setBzCheckReason(String bzCheckReason) {
		this.bzCheckReason = bzCheckReason;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessDTO [bzCode=");
		builder.append(bzCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bzCompanyName=");
		builder.append(bzCompanyName);
		builder.append(", bzCeoName=");
		builder.append(bzCeoName);
		builder.append(", bzZipcode=");
		builder.append(bzZipcode);
		builder.append(", bzAddr=");
		builder.append(bzAddr);
		builder.append(", bzDetailAddr=");
		builder.append(bzDetailAddr);
		builder.append(", bzPlace=");
		builder.append(bzPlace);
		builder.append(", bzLicense=");
		builder.append(bzLicense);
		builder.append(", bzType=");
		builder.append(bzType);
		builder.append(", bzRequestDate=");
		builder.append(bzRequestDate);
		builder.append(", bzCheck=");
		builder.append(bzCheck);
		builder.append(", bzCheckReason=");
		builder.append(bzCheckReason);
		builder.append("]");
		return builder.toString();
	}
	
	
}
