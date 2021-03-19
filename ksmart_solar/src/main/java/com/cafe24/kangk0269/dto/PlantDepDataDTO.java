package com.cafe24.kangk0269.dto;

public class PlantDepDataDTO {

	private int pl_dep_data_idx;
	private String bz_pl_code;
	private String m_id;
	private int pl_dep_price_based;
	private int pl_dep_data;
	private int pl_dep_data_residual;
	private int pl_dep_data_momth;
	public int getPl_dep_data_idx() {
		return pl_dep_data_idx;
	}
	public void setPl_dep_data_idx(int pl_dep_data_idx) {
		this.pl_dep_data_idx = pl_dep_data_idx;
	}
	public String getBz_pl_code() {
		return bz_pl_code;
	}
	public void setBz_pl_code(String bz_pl_code) {
		this.bz_pl_code = bz_pl_code;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getPl_dep_price_based() {
		return pl_dep_price_based;
	}
	public void setPl_dep_price_based(int pl_dep_price_based) {
		this.pl_dep_price_based = pl_dep_price_based;
	}
	public int getPl_dep_data() {
		return pl_dep_data;
	}
	public void setPl_dep_data(int pl_dep_data) {
		this.pl_dep_data = pl_dep_data;
	}
	public int getPl_dep_data_residual() {
		return pl_dep_data_residual;
	}
	public void setPl_dep_data_residual(int pl_dep_data_residual) {
		this.pl_dep_data_residual = pl_dep_data_residual;
	}
	public int getPl_dep_data_momth() {
		return pl_dep_data_momth;
	}
	public void setPl_dep_data_momth(int pl_dep_data_momth) {
		this.pl_dep_data_momth = pl_dep_data_momth;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlantDepDataDTO [pl_dep_data_idx=");
		builder.append(pl_dep_data_idx);
		builder.append(", bz_pl_code=");
		builder.append(bz_pl_code);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append(", pl_dep_price_based=");
		builder.append(pl_dep_price_based);
		builder.append(", pl_dep_data=");
		builder.append(pl_dep_data);
		builder.append(", pl_dep_data_residual=");
		builder.append(pl_dep_data_residual);
		builder.append(", pl_dep_data_momth=");
		builder.append(pl_dep_data_momth);
		builder.append("]");
		return builder.toString();
	}

}
