package com.cafe24.kangk0269.dto;

public class MemberRevokeDTO {

	private int m_revoke_idx;
	private String m_id;
	private String m_revoke_reason;
	private String m_revoke_date;
	private String m_revoke_cancel_date;
	private String m_revoke_final_date;
	public int getM_revoke_idx() {
		return m_revoke_idx;
	}
	public void setM_revoke_idx(int m_revoke_idx) {
		this.m_revoke_idx = m_revoke_idx;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_revoke_reason() {
		return m_revoke_reason;
	}
	public void setM_revoke_reason(String m_revoke_reason) {
		this.m_revoke_reason = m_revoke_reason;
	}
	public String getM_revoke_date() {
		return m_revoke_date;
	}
	public void setM_revoke_date(String m_revoke_date) {
		this.m_revoke_date = m_revoke_date;
	}
	public String getM_revoke_cancel_date() {
		return m_revoke_cancel_date;
	}
	public void setM_revoke_cancel_date(String m_revoke_cancel_date) {
		this.m_revoke_cancel_date = m_revoke_cancel_date;
	}
	public String getM_revoke_final_date() {
		return m_revoke_final_date;
	}
	public void setM_revoke_final_date(String m_revoke_final_date) {
		this.m_revoke_final_date = m_revoke_final_date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberRevoke [m_revoke_idx=");
		builder.append(m_revoke_idx);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append(", m_revoke_reason=");
		builder.append(m_revoke_reason);
		builder.append(", m_revoke_date=");
		builder.append(m_revoke_date);
		builder.append(", m_revoke_cancel_date=");
		builder.append(m_revoke_cancel_date);
		builder.append(", m_revoke_final_date=");
		builder.append(m_revoke_final_date);
		builder.append("]");
		return builder.toString();
	}

}
