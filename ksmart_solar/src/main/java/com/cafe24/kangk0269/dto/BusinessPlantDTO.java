package com.cafe24.kangk0269.dto;

public class BusinessPlantDTO {
	private String bz_pl_code;
	private String m_id;
	private String bz_pl_check;
	private String bz_pl_name;	
	private String bz_pl_zipcode;	
	private String bz_pl_addr;	
	private String bz_pl_detail_addr;	
	private String bz_pl_photo;
	private int bz_pl_power;
	private String bz_pl_hardware;	
	private int bz_pl_area;	
	private int bz_pl_inv_power;	
	private int bz_pl_inv_count;	
	private String bz_pl_inv_maker;	
	private int bz_pl_rec;
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
	public String getBz_pl_check() {
		return bz_pl_check;
	}
	public void setBz_pl_check(String bz_pl_check) {
		this.bz_pl_check = bz_pl_check;
	}
	public String getBz_pl_name() {
		return bz_pl_name;
	}
	public void setBz_pl_name(String bz_pl_name) {
		this.bz_pl_name = bz_pl_name;
	}
	public String getBz_pl_zipcode() {
		return bz_pl_zipcode;
	}
	public void setBz_pl_zipcode(String bz_pl_zipcode) {
		this.bz_pl_zipcode = bz_pl_zipcode;
	}
	public String getBz_pl_addr() {
		return bz_pl_addr;
	}
	public void setBz_pl_addr(String bz_pl_addr) {
		this.bz_pl_addr = bz_pl_addr;
	}
	public String getBz_pl_detail_addr() {
		return bz_pl_detail_addr;
	}
	public void setBz_pl_detail_addr(String bz_pl_detail_addr) {
		this.bz_pl_detail_addr = bz_pl_detail_addr;
	}
	public String getBz_pl_photo() {
		return bz_pl_photo;
	}
	public void setBz_pl_photo(String bz_pl_photo) {
		this.bz_pl_photo = bz_pl_photo;
	}
	public int getBz_pl_power() {
		return bz_pl_power;
	}
	public void setBz_pl_power(int bz_pl_power) {
		this.bz_pl_power = bz_pl_power;
	}
	public String getBz_pl_hardware() {
		return bz_pl_hardware;
	}
	public void setBz_pl_hardware(String bz_pl_hardware) {
		this.bz_pl_hardware = bz_pl_hardware;
	}
	public int getBz_pl_area() {
		return bz_pl_area;
	}
	public void setBz_pl_area(int bz_pl_area) {
		this.bz_pl_area = bz_pl_area;
	}
	public int getBz_pl_inv_power() {
		return bz_pl_inv_power;
	}
	public void setBz_pl_inv_power(int bz_pl_inv_power) {
		this.bz_pl_inv_power = bz_pl_inv_power;
	}
	public int getBz_pl_inv_count() {
		return bz_pl_inv_count;
	}
	public void setBz_pl_inv_count(int bz_pl_inv_count) {
		this.bz_pl_inv_count = bz_pl_inv_count;
	}
	public String getBz_pl_inv_maker() {
		return bz_pl_inv_maker;
	}
	public void setBz_pl_inv_maker(String bz_pl_inv_maker) {
		this.bz_pl_inv_maker = bz_pl_inv_maker;
	}
	public int getBz_pl_rec() {
		return bz_pl_rec;
	}
	public void setBz_pl_rec(int bz_pl_rec) {
		this.bz_pl_rec = bz_pl_rec;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessPlantDTO [bz_pl_code=");
		builder.append(bz_pl_code);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append(", bz_pl_check=");
		builder.append(bz_pl_check);
		builder.append(", bz_pl_name=");
		builder.append(bz_pl_name);
		builder.append(", bz_pl_zipcode=");
		builder.append(bz_pl_zipcode);
		builder.append(", bz_pl_addr=");
		builder.append(bz_pl_addr);
		builder.append(", bz_pl_detail_addr=");
		builder.append(bz_pl_detail_addr);
		builder.append(", bz_pl_photo=");
		builder.append(bz_pl_photo);
		builder.append(", bz_pl_power=");
		builder.append(bz_pl_power);
		builder.append(", bz_pl_hardware=");
		builder.append(bz_pl_hardware);
		builder.append(", bz_pl_area=");
		builder.append(bz_pl_area);
		builder.append(", bz_pl_inv_power=");
		builder.append(bz_pl_inv_power);
		builder.append(", bz_pl_inv_count=");
		builder.append(bz_pl_inv_count);
		builder.append(", bz_pl_inv_maker=");
		builder.append(bz_pl_inv_maker);
		builder.append(", bz_pl_rec=");
		builder.append(bz_pl_rec);
		builder.append("]");
		return builder.toString();
	}
	
}
