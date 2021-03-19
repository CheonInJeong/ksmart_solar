package com.cafe24.kangk0269.dto;

public class BidListDTO {
	private String b_code;
	private String announced_code;
	private String b_type_code;	
	private String m_id;
	private int b_price;
	private double s_deposit_rate;
	private int b_deposit;	
	private String b_deposit_check;
	private String b_mo_code;	
	private String b_deposit_date;
	private String tr_type_code;
	private String tr_type_name;	
	private String dc_instructions;	
	private String dc_application;	
	private String dc_proposal;	
	private String b_date;
	private String b_check;	
	private int b_rank;	
	private String b_deposit_available;
	private String b_deposit_refund;
	private String b_date_up;
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String b_code) {
		this.b_code = b_code;
	}
	public String getAnnounced_code() {
		return announced_code;
	}
	public void setAnnounced_code(String announced_code) {
		this.announced_code = announced_code;
	}
	public String getB_type_code() {
		return b_type_code;
	}
	public void setB_type_code(String b_type_code) {
		this.b_type_code = b_type_code;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getB_price() {
		return b_price;
	}
	public void setB_price(int b_price) {
		this.b_price = b_price;
	}
	public double getS_deposit_rate() {
		return s_deposit_rate;
	}
	public void setS_deposit_rate(double s_deposit_rate) {
		this.s_deposit_rate = s_deposit_rate;
	}
	public int getB_deposit() {
		return b_deposit;
	}
	public void setB_deposit(int b_deposit) {
		this.b_deposit = b_deposit;
	}
	public String getB_deposit_check() {
		return b_deposit_check;
	}
	public void setB_deposit_check(String b_deposit_check) {
		this.b_deposit_check = b_deposit_check;
	}
	public String getB_mo_code() {
		return b_mo_code;
	}
	public void setB_mo_code(String b_mo_code) {
		this.b_mo_code = b_mo_code;
	}
	public String getB_deposit_date() {
		return b_deposit_date;
	}
	public void setB_deposit_date(String b_deposit_date) {
		this.b_deposit_date = b_deposit_date;
	}
	public String getTr_type_code() {
		return tr_type_code;
	}
	public void setTr_type_code(String tr_type_code) {
		this.tr_type_code = tr_type_code;
	}
	public String getTr_type_name() {
		return tr_type_name;
	}
	public void setTr_type_name(String tr_type_name) {
		this.tr_type_name = tr_type_name;
	}
	public String getDc_instructions() {
		return dc_instructions;
	}
	public void setDc_instructions(String dc_instructions) {
		this.dc_instructions = dc_instructions;
	}
	public String getDc_application() {
		return dc_application;
	}
	public void setDc_application(String dc_application) {
		this.dc_application = dc_application;
	}
	public String getDc_proposal() {
		return dc_proposal;
	}
	public void setDc_proposal(String dc_proposal) {
		this.dc_proposal = dc_proposal;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public String getB_check() {
		return b_check;
	}
	public void setB_check(String b_check) {
		this.b_check = b_check;
	}
	public int getB_rank() {
		return b_rank;
	}
	public void setB_rank(int b_rank) {
		this.b_rank = b_rank;
	}
	public String getB_deposit_available() {
		return b_deposit_available;
	}
	public void setB_deposit_available(String b_deposit_available) {
		this.b_deposit_available = b_deposit_available;
	}
	public String getB_deposit_refund() {
		return b_deposit_refund;
	}
	public void setB_deposit_refund(String b_deposit_refund) {
		this.b_deposit_refund = b_deposit_refund;
	}
	public String getB_date_up() {
		return b_date_up;
	}
	public void setB_date_up(String b_date_up) {
		this.b_date_up = b_date_up;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BidListDTO [b_code=");
		builder.append(b_code);
		builder.append(", announced_code=");
		builder.append(announced_code);
		builder.append(", b_type_code=");
		builder.append(b_type_code);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append(", b_price=");
		builder.append(b_price);
		builder.append(", s_deposit_rate=");
		builder.append(s_deposit_rate);
		builder.append(", b_deposit=");
		builder.append(b_deposit);
		builder.append(", b_deposit_check=");
		builder.append(b_deposit_check);
		builder.append(", b_mo_code=");
		builder.append(b_mo_code);
		builder.append(", b_deposit_date=");
		builder.append(b_deposit_date);
		builder.append(", tr_type_code=");
		builder.append(tr_type_code);
		builder.append(", tr_type_name=");
		builder.append(tr_type_name);
		builder.append(", dc_instructions=");
		builder.append(dc_instructions);
		builder.append(", dc_application=");
		builder.append(dc_application);
		builder.append(", dc_proposal=");
		builder.append(dc_proposal);
		builder.append(", b_date=");
		builder.append(b_date);
		builder.append(", b_check=");
		builder.append(b_check);
		builder.append(", b_rank=");
		builder.append(b_rank);
		builder.append(", b_deposit_available=");
		builder.append(b_deposit_available);
		builder.append(", b_deposit_refund=");
		builder.append(b_deposit_refund);
		builder.append(", b_date_up=");
		builder.append(b_date_up);
		builder.append("]");
		return builder.toString();
	}

}
