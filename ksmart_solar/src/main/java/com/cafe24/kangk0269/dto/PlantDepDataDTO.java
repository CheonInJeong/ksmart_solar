package com.cafe24.kangk0269.dto;

public class PlantDepDataDTO {

	private int plDepDataIdx;
	private String bzPlCode;
	private String mId;
	private int plDepPriceBased;
	private int plDepData;
	private int plDepDataResidual;
	private int plDepDataMonth;
	public int getPlDepDataIdx() {
		return plDepDataIdx;
	}
	public void setPlDepDataIdx(int plDepDataIdx) {
		this.plDepDataIdx = plDepDataIdx;
	}
	public String getBzPlCode() {
		return bzPlCode;
	}
	public void setBzPlCode(String bzPlCode) {
		this.bzPlCode = bzPlCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getPlDepPriceBased() {
		return plDepPriceBased;
	}
	public void setPlDepPriceBased(int plDepPriceBased) {
		this.plDepPriceBased = plDepPriceBased;
	}
	public int getPlDepData() {
		return plDepData;
	}
	public void setPlDepData(int plDepData) {
		this.plDepData = plDepData;
	}
	public int getPlDepDataResidual() {
		return plDepDataResidual;
	}
	public void setPlDepDataResidual(int plDepDataResidual) {
		this.plDepDataResidual = plDepDataResidual;
	}
	public int getPlDepDataMonth() {
		return plDepDataMonth;
	}
	public void setPlDepDataMonth(int plDepDataMonth) {
		this.plDepDataMonth = plDepDataMonth;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantDepDataDTO [plDepDataIdx=");
		builder.append(plDepDataIdx);
		builder.append(", bzPlCode=");
		builder.append(bzPlCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", plDepPriceBased=");
		builder.append(plDepPriceBased);
		builder.append(", plDepData=");
		builder.append(plDepData);
		builder.append(", plDepDataResidual=");
		builder.append(plDepDataResidual);
		builder.append(", plDepDataMonth=");
		builder.append(plDepDataMonth);
		builder.append("]");
		return builder.toString();
	}
	

}
