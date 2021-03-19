package com.cafe24.kangk0269.dto;

public class PlantRadiationDTO {

	private int pl_rad_idx;
	private String pl_rad_date;
	private String pl_rad_location;
	private String pl_rad_icsr;
	private String pl_rad_ss;
	private String pl_rad_ssflg;
	private String pl_rad_ta;
	private String pl_rad_taflg;
	public int getPl_rad_idx() {
		return pl_rad_idx;
	}
	public void setPl_rad_idx(int pl_rad_idx) {
		this.pl_rad_idx = pl_rad_idx;
	}
	public String getPl_rad_date() {
		return pl_rad_date;
	}
	public void setPl_rad_date(String pl_rad_date) {
		this.pl_rad_date = pl_rad_date;
	}
	public String getPl_rad_location() {
		return pl_rad_location;
	}
	public void setPl_rad_location(String pl_rad_location) {
		this.pl_rad_location = pl_rad_location;
	}
	public String getPl_rad_icsr() {
		return pl_rad_icsr;
	}
	public void setPl_rad_icsr(String pl_rad_icsr) {
		this.pl_rad_icsr = pl_rad_icsr;
	}
	public String getPl_rad_ss() {
		return pl_rad_ss;
	}
	public void setPl_rad_ss(String pl_rad_ss) {
		this.pl_rad_ss = pl_rad_ss;
	}
	public String getPl_rad_ssflg() {
		return pl_rad_ssflg;
	}
	public void setPl_rad_ssflg(String pl_rad_ssflg) {
		this.pl_rad_ssflg = pl_rad_ssflg;
	}
	public String getPl_rad_ta() {
		return pl_rad_ta;
	}
	public void setPl_rad_ta(String pl_rad_ta) {
		this.pl_rad_ta = pl_rad_ta;
	}
	public String getPl_rad_taflg() {
		return pl_rad_taflg;
	}
	public void setPl_rad_taflg(String pl_rad_taflg) {
		this.pl_rad_taflg = pl_rad_taflg;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantRadiationDTO [pl_rad_idx=");
		builder.append(pl_rad_idx);
		builder.append(", pl_rad_date=");
		builder.append(pl_rad_date);
		builder.append(", pl_rad_location=");
		builder.append(pl_rad_location);
		builder.append(", pl_rad_icsr=");
		builder.append(pl_rad_icsr);
		builder.append(", pl_rad_ss=");
		builder.append(pl_rad_ss);
		builder.append(", pl_rad_ssflg=");
		builder.append(pl_rad_ssflg);
		builder.append(", pl_rad_ta=");
		builder.append(pl_rad_ta);
		builder.append(", pl_rad_taflg=");
		builder.append(pl_rad_taflg);
		builder.append("]");
		return builder.toString();
	}
	
}
