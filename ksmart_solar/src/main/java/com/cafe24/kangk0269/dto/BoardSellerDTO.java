package com.cafe24.kangk0269.dto;

public class BoardSellerDTO {
	private int bSellerIdx;
	private String mId;
	private String mIdSeller;
	private String bPlComponentCode;
	private String relatedTable;
	private String bSellerSubject;
	private String bSellerContents;	
	private int bSellerViews;
	private String bSellerPhoto;
	private int bSellerRefCode;
	private int bSellerReLev;
	private int bSellerReSeq;
	private String bSellerTemp;
	private String bSellerRegDate;
	public int getbSellerIdx() {
		return bSellerIdx;
	}
	public void setbSellerIdx(int bSellerIdx) {
		this.bSellerIdx = bSellerIdx;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmIdSeller() {
		return mIdSeller;
	}
	public void setmIdSeller(String mIdSeller) {
		this.mIdSeller = mIdSeller;
	}
	public String getbPlComponentCode() {
		return bPlComponentCode;
	}
	public void setbPlComponentCode(String bPlComponentCode) {
		this.bPlComponentCode = bPlComponentCode;
	}
	public String getRelatedTable() {
		return relatedTable;
	}
	public void setRelatedTable(String relatedTable) {
		this.relatedTable = relatedTable;
	}
	public String getbSellerSubject() {
		return bSellerSubject;
	}
	public void setbSellerSubject(String bSellerSubject) {
		this.bSellerSubject = bSellerSubject;
	}
	public String getbSellerContents() {
		return bSellerContents;
	}
	public void setbSellerContents(String bSellerContents) {
		this.bSellerContents = bSellerContents;
	}
	public int getbSellerViews() {
		return bSellerViews;
	}
	public void setbSellerViews(int bSellerViews) {
		this.bSellerViews = bSellerViews;
	}
	public String getbSellerPhoto() {
		return bSellerPhoto;
	}
	public void setbSellerPhoto(String bSellerPhoto) {
		this.bSellerPhoto = bSellerPhoto;
	}
	public int getbSellerRefCode() {
		return bSellerRefCode;
	}
	public void setbSellerRefCode(int bSellerRefCode) {
		this.bSellerRefCode = bSellerRefCode;
	}
	public int getbSellerReLev() {
		return bSellerReLev;
	}
	public void setbSellerReLev(int bSellerReLev) {
		this.bSellerReLev = bSellerReLev;
	}
	public int getbSellerReSeq() {
		return bSellerReSeq;
	}
	public void setbSellerReSeq(int bSellerReSeq) {
		this.bSellerReSeq = bSellerReSeq;
	}
	public String getbSellerTemp() {
		return bSellerTemp;
	}
	public void setbSellerTemp(String bSellerTemp) {
		this.bSellerTemp = bSellerTemp;
	}
	public String getbSellerRegDate() {
		return bSellerRegDate;
	}
	public void setbSellerRegDate(String bSellerRegDate) {
		this.bSellerRegDate = bSellerRegDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardSellerDTO [bSellerIdx=");
		builder.append(bSellerIdx);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", mIdSeller=");
		builder.append(mIdSeller);
		builder.append(", bPlComponentCode=");
		builder.append(bPlComponentCode);
		builder.append(", relatedTable=");
		builder.append(relatedTable);
		builder.append(", bSellerSubject=");
		builder.append(bSellerSubject);
		builder.append(", bSellerContents=");
		builder.append(bSellerContents);
		builder.append(", bSellerViews=");
		builder.append(bSellerViews);
		builder.append(", bSellerPhoto=");
		builder.append(bSellerPhoto);
		builder.append(", bSellerRefCode=");
		builder.append(bSellerRefCode);
		builder.append(", bSellerReLev=");
		builder.append(bSellerReLev);
		builder.append(", bSellerReSeq=");
		builder.append(bSellerReSeq);
		builder.append(", bSellerTemp=");
		builder.append(bSellerTemp);
		builder.append(", bSellerRegDate=");
		builder.append(bSellerRegDate);
		builder.append("]");
		return builder.toString();
	}

}
