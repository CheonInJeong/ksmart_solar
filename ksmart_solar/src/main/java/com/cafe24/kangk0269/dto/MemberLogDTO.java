package com.cafe24.kangk0269.dto;

public class MemberLogDTO {

	private int m_log_idx;
	private String m_id;
	private String m_log_in;
	private String m_log_out;
	public int getM_log_idx() {
		return m_log_idx;
	}
	public void setM_log_idx(int m_log_idx) {
		this.m_log_idx = m_log_idx;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_log_in() {
		return m_log_in;
	}
	public void setM_log_in(String m_log_in) {
		this.m_log_in = m_log_in;
	}
	public String getM_log_out() {
		return m_log_out;
	}
	public void setM_log_out(String m_log_out) {
		this.m_log_out = m_log_out;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberLogDTO [m_log_idx=");
		builder.append(m_log_idx);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append(", m_log_in=");
		builder.append(m_log_in);
		builder.append(", m_log_out=");
		builder.append(m_log_out);
		builder.append("]");
		return builder.toString();
	}

}
