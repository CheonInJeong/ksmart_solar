package com.cafe24.kangk0269.dto;

public class PlantDataDTO {
	private int pl_data_idx;	
	private String bz_pl_code;	
	private int pl_data_energy;	
	private int pl_data_voltage;	
	private int pl_data_current;
	private String pl_data_date;
	public int getPl_data_idx() {
		return pl_data_idx;
	}
	public void setPl_data_idx(int pl_data_idx) {
		this.pl_data_idx = pl_data_idx;
	}
	public String getBz_pl_code() {
		return bz_pl_code;
	}
	public void setBz_pl_code(String bz_pl_code) {
		this.bz_pl_code = bz_pl_code;
	}
	public int getPl_data_energy() {
		return pl_data_energy;
	}
	public void setPl_data_energy(int pl_data_energy) {
		this.pl_data_energy = pl_data_energy;
	}
	public int getPl_data_voltage() {
		return pl_data_voltage;
	}
	public void setPl_data_voltage(int pl_data_voltage) {
		this.pl_data_voltage = pl_data_voltage;
	}
	public int getPl_data_current() {
		return pl_data_current;
	}
	public void setPl_data_current(int pl_data_current) {
		this.pl_data_current = pl_data_current;
	}
	public String getPl_data_date() {
		return pl_data_date;
	}
	public void setPl_data_date(String pl_data_date) {
		this.pl_data_date = pl_data_date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantDataDTO [pl_data_idx=");
		builder.append(pl_data_idx);
		builder.append(", bz_pl_code=");
		builder.append(bz_pl_code);
		builder.append(", pl_data_energy=");
		builder.append(pl_data_energy);
		builder.append(", pl_data_voltage=");
		builder.append(pl_data_voltage);
		builder.append(", pl_data_current=");
		builder.append(pl_data_current);
		builder.append(", pl_data_date=");
		builder.append(pl_data_date);
		builder.append("]");
		return builder.toString();
	}
	
}
