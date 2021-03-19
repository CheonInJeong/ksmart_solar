package com.cafe24.kangk0269.dto;

public class MemberLevelDTO {

	private int m_level;
	private String m_level_name;
	private String m_level_date;
	public int getM_level() {
		return m_level;
	}
	public void setM_level(int m_level) {
		this.m_level = m_level;
	}
	public String getM_level_name() {
		return m_level_name;
	}
	public void setM_level_name(String m_level_name) {
		this.m_level_name = m_level_name;
	}
	public String getM_level_date() {
		return m_level_date;
	}
	public void setM_level_date(String m_level_date) {
		this.m_level_date = m_level_date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberLevelDTO [m_level=");
		builder.append(m_level);
		builder.append(", m_level_name=");
		builder.append(m_level_name);
		builder.append(", m_level_date=");
		builder.append(m_level_date);
		builder.append("]");
		return builder.toString();
	}

}
