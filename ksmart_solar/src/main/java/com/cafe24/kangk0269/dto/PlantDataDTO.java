package com.cafe24.kangk0269.dto;

public class PlantDataDTO {
	private int plDataIdx;	
	private String bzPlCode;	
	private int plDataEnergy;	
	private int plDataVoltage;	
	private int plDataCurrent;
	private String plDataDate;
	public int getPlDataIdx() {
		return plDataIdx;
	}
	public void setPlDataIdx(int plDataIdx) {
		this.plDataIdx = plDataIdx;
	}
	public String getBzPlCode() {
		return bzPlCode;
	}
	public void setBzPlCode(String bzPlCode) {
		this.bzPlCode = bzPlCode;
	}
	public int getPlDataEnergy() {
		return plDataEnergy;
	}
	public void setPlDataEnergy(int plDataEnergy) {
		this.plDataEnergy = plDataEnergy;
	}
	public int getPlDataVoltage() {
		return plDataVoltage;
	}
	public void setPlDataVoltage(int plDataVoltage) {
		this.plDataVoltage = plDataVoltage;
	}
	public int getPlDataCurrent() {
		return plDataCurrent;
	}
	public void setPlDataCurrent(int plDataCurrent) {
		this.plDataCurrent = plDataCurrent;
	}
	public String getPlDataDate() {
		return plDataDate;
	}
	public void setPlDataDate(String plDataDate) {
		this.plDataDate = plDataDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantDataDTO [plDataIdx=");
		builder.append(plDataIdx);
		builder.append(", bzPlCode=");
		builder.append(bzPlCode);
		builder.append(", plDataEnergy=");
		builder.append(plDataEnergy);
		builder.append(", plDataVoltage=");
		builder.append(plDataVoltage);
		builder.append(", plDataCurrent=");
		builder.append(plDataCurrent);
		builder.append(", plDataDate=");
		builder.append(plDataDate);
		builder.append("]");
		return builder.toString();
	}
	
}
