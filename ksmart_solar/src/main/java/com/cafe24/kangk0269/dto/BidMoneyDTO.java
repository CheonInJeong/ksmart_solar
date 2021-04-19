package com.cafe24.kangk0269.dto;

public class BidMoneyDTO {
	private String bMoCode;
	private String relatedCode;
	private String mId;
	private String bMoType;
	private String relatedTable;
	private int bMoAmount;	
	private String bMoDetail;	
	private String mIdManager;
	private int total;
	private int bMoTotalmoney;
	private String bMoDate;
	public String getbMoCode() {
		return bMoCode;
	}
	public void setbMoCode(String bMoCode) {
		this.bMoCode = bMoCode;
	}
	public String getRelatedCode() {
		return relatedCode;
	}
	public void setRelatedCode(String relatedCode) {
		this.relatedCode = relatedCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getbMoType() {
		return bMoType;
	}
	public void setbMoType(String bMoType) {
		this.bMoType = bMoType;
	}
	public String getRelatedTable() {
		return relatedTable;
	}
	public void setRelatedTable(String relatedTable) {
		this.relatedTable = relatedTable;
	}
	public int getbMoAmount() {
		return bMoAmount;
	}
	public void setbMoAmount(int bMoAmount) {
		this.bMoAmount = bMoAmount;
	}
	public String getbMoDetail() {
		return bMoDetail;
	}
	public void setbMoDetail(String bMoDetail) {
		this.bMoDetail = bMoDetail;
	}
	public String getmIdManager() {
		return mIdManager;
	}
	public void setmIdManager(String mIdManager) {
		this.mIdManager = mIdManager;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getbMoTotalmoney() {
		return bMoTotalmoney;
	}
	public void setbMoTotalmoney(int bMoTotalmoney) {
		this.bMoTotalmoney = bMoTotalmoney;
	}
	public String getbMoDate() {
		return bMoDate;
	}
	public void setbMoDate(String bMoDate) {
		this.bMoDate = bMoDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidMoneyDTO [bMoCode=");
		builder.append(bMoCode);
		builder.append(", relatedCode=");
		builder.append(relatedCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bMoType=");
		builder.append(bMoType);
		builder.append(", relatedTable=");
		builder.append(relatedTable);
		builder.append(", bMoAmount=");
		builder.append(bMoAmount);
		builder.append(", bMoDetail=");
		builder.append(bMoDetail);
		builder.append(", mIdManager=");
		builder.append(mIdManager);
		builder.append(", total=");
		builder.append(total);
		builder.append(", bMoTotalmoney=");
		builder.append(bMoTotalmoney);
		builder.append(", bMoDate=");
		builder.append(bMoDate);
		builder.append("]");
		return builder.toString();
	}
	

}
