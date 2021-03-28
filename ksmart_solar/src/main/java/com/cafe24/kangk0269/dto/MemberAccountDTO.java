package com.cafe24.kangk0269.dto;


public class MemberAccountDTO {

	private int mAccountIdx;
	private String mId;	
	private String mAccountNumber;	
	private String mAccountName;
	private String mAccountBank;	
	private String mAccountRegDate;	
	private String mAccountCheck;
	public int getmAccountIdx() {
		return mAccountIdx;
	}
	public void setmAccountIdx(int mAccountIdx) {
		this.mAccountIdx = mAccountIdx;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmAccountNumber() {
		return mAccountNumber;
	}
	public void setmAccountNumber(String mAccountNumber) {
		this.mAccountNumber = mAccountNumber;
	}
	public String getmAccountName() {
		return mAccountName;
	}
	public void setmAccountName(String mAccountName) {
		this.mAccountName = mAccountName;
	}
	public String getmAccountBank() {
		return mAccountBank;
	}
	public void setmAccountBank(String mAccountBank) {
		this.mAccountBank = mAccountBank;
	}
	public String getmAccountRegDate() {
		return mAccountRegDate;
	}
	public void setmAccountRegDate(String mAccountRegDate) {
		this.mAccountRegDate = mAccountRegDate;
	}
	public String getmAccountCheck() {
		return mAccountCheck;
	}
	public void setmAccountCheck(String mAccountCheck) {
		this.mAccountCheck = mAccountCheck;
	}
	@Override
	public String toString() {
		return "MemberAccountDTO [mAccountIdx=" + mAccountIdx + ", mId=" + mId + ", mAccountNumber=" + mAccountNumber
				+ ", mAccountName=" + mAccountName + ", mAccountBank=" + mAccountBank + ", mAccountRegDate="
				+ mAccountRegDate + ", mAccountCheck=" + mAccountCheck + "]";
	}


	
}
