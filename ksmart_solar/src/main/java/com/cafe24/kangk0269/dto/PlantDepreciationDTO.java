package com.cafe24.kangk0269.dto;

public class PlantDepreciationDTO {
	private int pl_dep_idx;	
	private String bz_pl_code;	
	private int pl_dep_price_based;	
	private int pl_dep_price;	
	private String pl_dep_start_date;	
	private String pl_dep_buy_date;	
	private int pl_dep_maintenance;	
	private String pl_dep_used;	
	private int pl_dep_servicelife;
	public int getPl_dep_idx() {
		return pl_dep_idx;
	}
	public void setPl_dep_idx(int pl_dep_idx) {
		this.pl_dep_idx = pl_dep_idx;
	}
	public String getBz_pl_code() {
		return bz_pl_code;
	}
	public void setBz_pl_code(String bz_pl_code) {
		this.bz_pl_code = bz_pl_code;
	}
	public int getPl_dep_price_based() {
		return pl_dep_price_based;
	}
	public void setPl_dep_price_based(int pl_dep_price_based) {
		this.pl_dep_price_based = pl_dep_price_based;
	}
	public int getPl_dep_price() {
		return pl_dep_price;
	}
	public void setPl_dep_price(int pl_dep_price) {
		this.pl_dep_price = pl_dep_price;
	}
	public String getPl_dep_start_date() {
		return pl_dep_start_date;
	}
	public void setPl_dep_start_date(String pl_dep_start_date) {
		this.pl_dep_start_date = pl_dep_start_date;
	}
	public String getPl_dep_buy_date() {
		return pl_dep_buy_date;
	}
	public void setPl_dep_buy_date(String pl_dep_buy_date) {
		this.pl_dep_buy_date = pl_dep_buy_date;
	}
	public int getPl_dep_maintenance() {
		return pl_dep_maintenance;
	}
	public void setPl_dep_maintenance(int pl_dep_maintenance) {
		this.pl_dep_maintenance = pl_dep_maintenance;
	}
	public String getPl_dep_used() {
		return pl_dep_used;
	}
	public void setPl_dep_used(String pl_dep_used) {
		this.pl_dep_used = pl_dep_used;
	}
	public int getPl_dep_servicelife() {
		return pl_dep_servicelife;
	}
	public void setPl_dep_servicelife(int pl_dep_servicelife) {
		this.pl_dep_servicelife = pl_dep_servicelife;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantDepreciation [pl_dep_idx=");
		builder.append(pl_dep_idx);
		builder.append(", bz_pl_code=");
		builder.append(bz_pl_code);
		builder.append(", pl_dep_price_based=");
		builder.append(pl_dep_price_based);
		builder.append(", pl_dep_price=");
		builder.append(pl_dep_price);
		builder.append(", pl_dep_start_date=");
		builder.append(pl_dep_start_date);
		builder.append(", pl_dep_buy_date=");
		builder.append(pl_dep_buy_date);
		builder.append(", pl_dep_maintenance=");
		builder.append(pl_dep_maintenance);
		builder.append(", pl_dep_used=");
		builder.append(pl_dep_used);
		builder.append(", pl_dep_servicelife=");
		builder.append(pl_dep_servicelife);
		builder.append("]");
		return builder.toString();
	}
	
}
