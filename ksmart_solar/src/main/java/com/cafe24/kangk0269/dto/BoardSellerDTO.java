package com.cafe24.kangk0269.dto;

public class BoardSellerDTO  {
	
	 private int bIdx ; 
	 private String bSubject ; 
	 private String bContents ; 
	 private String mIdBuyer ; 
	 private String mIdSeller ; 
	 private String announcedCode ;
	 private String bView ; 
	 private String bPhoto ; 
	 private String bRegDate;
	 private String bAnswer;
	 private int bBidType;
	 
	 
	public int getbBidType() {
		return bBidType;
	}
	public void setbBidType(int bBidType) {
		this.bBidType = bBidType;
	}
	public int getbIdx() {
		return bIdx;
	}
	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}
	public String getbSubject() {
		return bSubject;
	}
	public void setbSubject(String bSubject) {
		this.bSubject = bSubject;
	}
	public String getbContents() {
		return bContents;
	}
	public void setbContents(String bContents) {
		this.bContents = bContents;
	}
	public String getmIdBuyer() {
		return mIdBuyer;
	}
	public void setmIdBuyer(String mIdBuyer) {
		this.mIdBuyer = mIdBuyer;
	}
	public String getmIdSeller() {
		return mIdSeller;
	}
	public void setmIdSeller(String mIdSeller) {
		this.mIdSeller = mIdSeller;
	}
	public String getAnnouncedCode() {
		return announcedCode;
	}
	public void setAnnouncedCode(String announcedCode) {
		this.announcedCode = announcedCode;
	}
	public String getbView() {
		return bView;
	}
	public void setbView(String bView) {
		this.bView = bView;
	}
	public String getbPhoto() {
		return bPhoto;
	}
	public void setbPhoto(String bPhoto) {
		this.bPhoto = bPhoto;
	}
	public String getbRegDate() {
		return bRegDate;
	}
	public void setbRegDate(String bRegDate) {
		this.bRegDate = bRegDate;
	}
	public String getbAnswer() {
		return bAnswer;
	}
	public void setbAnswer(String bAnswer) {
		this.bAnswer = bAnswer;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardSellerDTO [bIdx=");
		builder.append(bIdx);
		builder.append(", bSubject=");
		builder.append(bSubject);
		builder.append(", bContents=");
		builder.append(bContents);
		builder.append(", mIdBuyer=");
		builder.append(mIdBuyer);
		builder.append(", mIdSeller=");
		builder.append(mIdSeller);
		builder.append(", announcedCode=");
		builder.append(announcedCode);
		builder.append(", bView=");
		builder.append(bView);
		builder.append(", bPhoto=");
		builder.append(bPhoto);
		builder.append(", bRegDate=");
		builder.append(bRegDate);
		builder.append(", bAnswer=");
		builder.append(bAnswer);
		builder.append(", bBidType=");
		builder.append(bBidType);
		builder.append("]");
		return builder.toString();
	}
	
	
	 
 
	 
}
