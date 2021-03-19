package com.cafe24.kangk0269.dto;

public class MemberRestDTO {
	private int m_rest_idx;	
	private String m_id;
	private String m_rest_start_date;
	private String m_rest_end_date;
	public int getM_rest_idx() {
		return m_rest_idx;
	}
	public void setM_rest_idx(int m_rest_idx) {
		this.m_rest_idx = m_rest_idx;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_rest_start_date() {
		return m_rest_start_date;
	}
	public void setM_rest_start_date(String m_rest_start_date) {
		this.m_rest_start_date = m_rest_start_date;
	}
	public String getM_rest_end_date() {
		return m_rest_end_date;
	}
	public void setM_rest_end_date(String m_rest_end_date) {
		this.m_rest_end_date = m_rest_end_date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberRestDTO [m_rest_idx=");
		builder.append(m_rest_idx);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append(", m_rest_start_date=");
		builder.append(m_rest_start_date);
		builder.append(", m_rest_end_date=");
		builder.append(m_rest_end_date);
		builder.append("]");
		return builder.toString();
	}
	

}
