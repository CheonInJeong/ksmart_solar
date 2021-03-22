package com.cafe24.kangk0269.dto;

public class TradeDepositOutDTO {
	private String tr_dep_code;
	private String b_code;
	private String m_id;
	private int b_deposit;
	private int tr_dep_deposit;
	private String m_account_bank_name;
	private String m_account_number;
	private String tr_dep_account_check;
	private String m_account_name;
	private String tr_dep_date;
	private String tr_dep_check;
	private String tr_dep_wd_date;
	private String b_mo_code;
	public String getTr_dep_code() {
		return tr_dep_code;
	}
	public void setTr_dep_code(String tr_dep_code) {
		this.tr_dep_code = tr_dep_code;
	}
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String b_code) {
		this.b_code = b_code;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getB_deposit() {
		return b_deposit;
	}
	public void setB_deposit(int b_deposit) {
		this.b_deposit = b_deposit;
	}
	public int getTr_dep_deposit() {
		return tr_dep_deposit;
	}
	public void setTr_dep_deposit(int tr_dep_deposit) {
		this.tr_dep_deposit = tr_dep_deposit;
	}
	public String getM_account_bank_name() {
		return m_account_bank_name;
	}
	public void setM_account_bank_name(String m_account_bank_name) {
		this.m_account_bank_name = m_account_bank_name;
	}
	public String getM_account_number() {
		return m_account_number;
	}
	public void setM_account_number(String m_account_number) {
		this.m_account_number = m_account_number;
	}
	public String getTr_dep_account_check() {
		return tr_dep_account_check;
	}
	public void setTr_dep_account_check(String tr_dep_account_check) {
		this.tr_dep_account_check = tr_dep_account_check;
	}
	public String getM_account_name() {
		return m_account_name;
	}
	public void setM_account_name(String m_account_name) {
		this.m_account_name = m_account_name;
	}
	public String getTr_dep_date() {
		return tr_dep_date;
	}
	public void setTr_dep_date(String tr_dep_date) {
		this.tr_dep_date = tr_dep_date;
	}
	public String getTr_dep_check() {
		return tr_dep_check;
	}
	public void setTr_dep_check(String tr_dep_check) {
		this.tr_dep_check = tr_dep_check;
	}
	public String getTr_dep_wd_date() {
		return tr_dep_wd_date;
	}
	public void setTr_dep_wd_date(String tr_dep_wd_date) {
		this.tr_dep_wd_date = tr_dep_wd_date;
	}
	public String getB_mo_code() {
		return b_mo_code;
	}
	public void setB_mo_code(String b_mo_code) {
		this.b_mo_code = b_mo_code;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeDepositOutDTO [tr_dep_code=");
		builder.append(tr_dep_code);
		builder.append(", b_code=");
		builder.append(b_code);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append(", b_deposit=");
		builder.append(b_deposit);
		builder.append(", tr_dep_deposit=");
		builder.append(tr_dep_deposit);
		builder.append(", m_account_bank_name=");
		builder.append(m_account_bank_name);
		builder.append(", m_account_number=");
		builder.append(m_account_number);
		builder.append(", tr_dep_account_check=");
		builder.append(tr_dep_account_check);
		builder.append(", m_account_name=");
		builder.append(m_account_name);
		builder.append(", tr_dep_date=");
		builder.append(tr_dep_date);
		builder.append(", tr_dep_check=");
		builder.append(tr_dep_check);
		builder.append(", tr_dep_wd_date=");
		builder.append(tr_dep_wd_date);
		builder.append(", b_mo_code=");
		builder.append(b_mo_code);
		builder.append("]");
		return builder.toString();
	}

}
