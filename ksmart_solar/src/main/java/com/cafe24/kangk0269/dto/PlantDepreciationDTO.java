package com.cafe24.kangk0269.dto;

public class PlantDepreciationDTO {
	private int plDepIdx;	
	private String bzPlCode;	
	private int pl_depPriceBased;	
	private int plDepPrice;	
	private String plDepStartDate;	
	private String plDepBuyDate;	
	private int plDepMaintenance;	
	private String plDepUsed;	
	private int plDepServicelife;
	
	public int getPlDepIdx() {
		return plDepIdx;
	}
	public void setPlDepIdx(int plDepIdx) {
		this.plDepIdx = plDepIdx;
	}
	public String getBzPlCode() {
		return bzPlCode;
	}
	public void setBzPlCode(String bzPlCode) {
		this.bzPlCode = bzPlCode;
	}
	public int getPl_depPriceBased() {
		return pl_depPriceBased;
	}
	public void setPl_depPriceBased(int pl_depPriceBased) {
		this.pl_depPriceBased = pl_depPriceBased;
	}
	public int getPlDepPrice() {
		return plDepPrice;
	}
	public void setPlDepPrice(int plDepPrice) {
		this.plDepPrice = plDepPrice;
	}
	public String getPlDepStartDate() {
		return plDepStartDate;
	}
	public void setPlDepStartDate(String plDepStartDate) {
		this.plDepStartDate = plDepStartDate;
	}
	public String getPlDepBuyDate() {
		return plDepBuyDate;
	}
	public void setPlDepBuyDate(String plDepBuyDate) {
		this.plDepBuyDate = plDepBuyDate;
	}
	public int getPlDepMaintenance() {
		return plDepMaintenance;
	}
	public void setPlDepMaintenance(int plDepMaintenance) {
		this.plDepMaintenance = plDepMaintenance;
	}
	public String getPlDepUsed() {
		return plDepUsed;
	}
	public void setPlDepUsed(String plDepUsed) {
		this.plDepUsed = plDepUsed;
	}
	public int getPlDepServicelife() {
		return plDepServicelife;
	}
	public void setPlDepServicelife(int plDepServicelife) {
		this.plDepServicelife = plDepServicelife;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantDepreciationDTO [plDepIdx=");
		builder.append(plDepIdx);
		builder.append(", bzPlCode=");
		builder.append(bzPlCode);
		builder.append(", pl_depPriceBased=");
		builder.append(pl_depPriceBased);
		builder.append(", plDepPrice=");
		builder.append(plDepPrice);
		builder.append(", plDepStartDate=");
		builder.append(plDepStartDate);
		builder.append(", plDepBuyDate=");
		builder.append(plDepBuyDate);
		builder.append(", plDepMaintenance=");
		builder.append(plDepMaintenance);
		builder.append(", plDepUsed=");
		builder.append(plDepUsed);
		builder.append(", plDepServicelife=");
		builder.append(plDepServicelife);
		builder.append("]");
		return builder.toString();
	}
	
	
}
