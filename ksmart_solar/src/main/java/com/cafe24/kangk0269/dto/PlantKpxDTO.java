package com.cafe24.kangk0269.dto;

public class PlantKpxDTO {

	private int pl_kpx_idx;
	private String pl_kpx_time;
	private String pl_kpx_smp;
	private String pl_kpx_rec;
	public int getPl_kpx_idx() {
		return pl_kpx_idx;
	}
	public void setPl_kpx_idx(int pl_kpx_idx) {
		this.pl_kpx_idx = pl_kpx_idx;
	}
	public String getPl_kpx_time() {
		return pl_kpx_time;
	}
	public void setPl_kpx_time(String pl_kpx_time) {
		this.pl_kpx_time = pl_kpx_time;
	}
	public String getPl_kpx_smp() {
		return pl_kpx_smp;
	}
	public void setPl_kpx_smp(String pl_kpx_smp) {
		this.pl_kpx_smp = pl_kpx_smp;
	}
	public String getPl_kpx_rec() {
		return pl_kpx_rec;
	}
	public void setPl_kpx_rec(String pl_kpx_rec) {
		this.pl_kpx_rec = pl_kpx_rec;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantKpx [pl_kpx_idx=");
		builder.append(pl_kpx_idx);
		builder.append(", pl_kpx_time=");
		builder.append(pl_kpx_time);
		builder.append(", pl_kpx_smp=");
		builder.append(pl_kpx_smp);
		builder.append(", pl_kpx_rec=");
		builder.append(pl_kpx_rec);
		builder.append("]");
		return builder.toString();
	}

}
