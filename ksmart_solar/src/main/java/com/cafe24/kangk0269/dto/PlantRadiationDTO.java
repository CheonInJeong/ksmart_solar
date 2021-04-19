package com.cafe24.kangk0269.dto;

import lombok.Data;

public class PlantRadiationDTO {
	private int plRadIdx;
	private String plRadDate;
	private String plRadLocation;
	private String plRadIcsr;
	private String plRadSs;
	private String plRadSsflg;
	private String plRadTa;
	private String plRadTaflg;
	public int getPlRadIdx() {
		return plRadIdx;
	}
	public void setPlRadIdx(int plRadIdx) {
		this.plRadIdx = plRadIdx;
	}
	public String getPlRadDate() {
		return plRadDate;
	}
	public void setPlRadDate(String plRadDate) {
		this.plRadDate = plRadDate;
	}
	public String getPlRadLocation() {
		return plRadLocation;
	}
	public void setPlRadLocation(String plRadLocation) {
		this.plRadLocation = plRadLocation;
	}
	public String getPlRadIcsr() {
		return plRadIcsr;
	}
	public void setPlRadIcsr(String plRadIcsr) {
		this.plRadIcsr = plRadIcsr;
	}
	public String getPlRadSs() {
		return plRadSs;
	}
	public void setPlRadSs(String plRadSs) {
		this.plRadSs = plRadSs;
	}
	public String getPlRadSsflg() {
		return plRadSsflg;
	}
	public void setPlRadSsflg(String plRadSsflg) {
		this.plRadSsflg = plRadSsflg;
	}
	public String getPlRadTa() {
		return plRadTa;
	}
	public void setPlRadTa(String plRadTa) {
		this.plRadTa = plRadTa;
	}
	public String getPlRadTaflg() {
		return plRadTaflg;
	}
	public void setPlRadTaflg(String plRadTaflg) {
		this.plRadTaflg = plRadTaflg;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantRadiationDTO [plRadIdx=");
		builder.append(plRadIdx);
		builder.append(", plRadDate=");
		builder.append(plRadDate);
		builder.append(", plRadLocation=");
		builder.append(plRadLocation);
		builder.append(", plRadIcsr=");
		builder.append(plRadIcsr);
		builder.append(", plRadSs=");
		builder.append(plRadSs);
		builder.append(", plRadSsflg=");
		builder.append(plRadSsflg);
		builder.append(", plRadTa=");
		builder.append(plRadTa);
		builder.append(", plRadTaflg=");
		builder.append(plRadTaflg);
		builder.append("]");
		return builder.toString();
	}
	
}
