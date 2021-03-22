package com.cafe24.kangk0269.dto;

public class PlantKpxDTO {

	private int plKpxIdx;
	private String plKpxTime;
	private String plKpxSmp;
	private String plKpxRec;
	public int getPlKpxIdx() {
		return plKpxIdx;
	}
	public void setPlKpxIdx(int plKpxIdx) {
		this.plKpxIdx = plKpxIdx;
	}
	public String getPlKpxTime() {
		return plKpxTime;
	}
	public void setPlKpxTime(String plKpxTime) {
		this.plKpxTime = plKpxTime;
	}
	public String getPlKpxSmp() {
		return plKpxSmp;
	}
	public void setPlKpxSmp(String plKpxSmp) {
		this.plKpxSmp = plKpxSmp;
	}
	public String getPlKpxRec() {
		return plKpxRec;
	}
	public void setPlKpxRec(String plKpxRec) {
		this.plKpxRec = plKpxRec;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantKpxDTO [plKpxIdx=");
		builder.append(plKpxIdx);
		builder.append(", plKpxTime=");
		builder.append(plKpxTime);
		builder.append(", plKpxSmp=");
		builder.append(plKpxSmp);
		builder.append(", plKpxRec=");
		builder.append(plKpxRec);
		builder.append("]");
		return builder.toString();
	}
	
}
