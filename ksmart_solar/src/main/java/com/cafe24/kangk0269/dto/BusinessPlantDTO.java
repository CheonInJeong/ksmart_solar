package com.cafe24.kangk0269.dto;

public class BusinessPlantDTO {
	private String bzPlCode;
	private String mId;
	private String bzPlCheck;
	private String bzPlName;	
	private String bzPlZipcode;	
	private String bzPlAddr;	
	private String bzPlDetail_addr;	
	private String bzPlPhoto;
	private int bzPlPower;
	private String bzPlHardware;	
	private int bzPlArea;	
	private int bzPlInvPower;	
	private int bzPlInvCount;	
	private String bzPlInvMaker;	
	private int bzPlRec;
	private PlantDepreciationDTO plantDepreciationDTO;
	private PlantDepDataDTO plantDepDataDTO;
	public String getBzPlCode() {
		return bzPlCode;
	}
	public void setBzPlCode(String bzPlCode) {
		this.bzPlCode = bzPlCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getBzPlCheck() {
		return bzPlCheck;
	}
	public void setBzPlCheck(String bzPlCheck) {
		this.bzPlCheck = bzPlCheck;
	}
	public String getBzPlName() {
		return bzPlName;
	}
	public void setBzPlName(String bzPlName) {
		this.bzPlName = bzPlName;
	}
	public String getBzPlZipcode() {
		return bzPlZipcode;
	}
	public void setBzPlZipcode(String bzPlZipcode) {
		this.bzPlZipcode = bzPlZipcode;
	}
	public String getBzPlAddr() {
		return bzPlAddr;
	}
	public void setBzPlAddr(String bzPlAddr) {
		this.bzPlAddr = bzPlAddr;
	}
	public String getBzPlDetail_addr() {
		return bzPlDetail_addr;
	}
	public void setBzPlDetail_addr(String bzPlDetail_addr) {
		this.bzPlDetail_addr = bzPlDetail_addr;
	}
	public String getBzPlPhoto() {
		return bzPlPhoto;
	}
	public void setBzPlPhoto(String bzPlPhoto) {
		this.bzPlPhoto = bzPlPhoto;
	}
	public int getBzPlPower() {
		return bzPlPower;
	}
	public void setBzPlPower(int bzPlPower) {
		this.bzPlPower = bzPlPower;
	}
	public String getBzPlHardware() {
		return bzPlHardware;
	}
	public void setBzPlHardware(String bzPlHardware) {
		this.bzPlHardware = bzPlHardware;
	}
	public int getBzPlArea() {
		return bzPlArea;
	}
	public void setBzPlArea(int bzPlArea) {
		this.bzPlArea = bzPlArea;
	}
	public int getBzPlInvPower() {
		return bzPlInvPower;
	}
	public void setBzPlInvPower(int bzPlInvPower) {
		this.bzPlInvPower = bzPlInvPower;
	}
	public int getBzPlInvCount() {
		return bzPlInvCount;
	}
	public void setBzPlInvCount(int bzPlInvCount) {
		this.bzPlInvCount = bzPlInvCount;
	}
	public String getBzPlInvMaker() {
		return bzPlInvMaker;
	}
	public void setBzPlInvMaker(String bzPlInvMaker) {
		this.bzPlInvMaker = bzPlInvMaker;
	}
	public int getBzPlRec() {
		return bzPlRec;
	}
	public void setBzPlRec(int bzPlRec) {
		this.bzPlRec = bzPlRec;
	}
	public PlantDepreciationDTO getPlantDepreciationDTO() {
		return plantDepreciationDTO;
	}
	public void setPlantDepreciationDTO(PlantDepreciationDTO plantDepreciationDTO) {
		this.plantDepreciationDTO = plantDepreciationDTO;
	}
	public PlantDepDataDTO getPlantDepDataDTO() {
		return plantDepDataDTO;
	}
	public void setPlantDepDataDTO(PlantDepDataDTO plantDepDataDTO) {
		this.plantDepDataDTO = plantDepDataDTO;
	}
	@Override
	public String toString() {
		return "BusinessPlantDTO [bzPlCode=" + bzPlCode + ", mId=" + mId + ", bzPlCheck=" + bzPlCheck + ", bzPlName="
				+ bzPlName + ", bzPlZipcode=" + bzPlZipcode + ", bzPlAddr=" + bzPlAddr + ", bzPlDetail_addr="
				+ bzPlDetail_addr + ", bzPlPhoto=" + bzPlPhoto + ", bzPlPower=" + bzPlPower + ", bzPlHardware="
				+ bzPlHardware + ", bzPlArea=" + bzPlArea + ", bzPlInvPower=" + bzPlInvPower + ", bzPlInvCount="
				+ bzPlInvCount + ", bzPlInvMaker=" + bzPlInvMaker + ", bzPlRec=" + bzPlRec + ", plantDepreciationDTO="
				+ plantDepreciationDTO + ", plantDepDataDTO=" + plantDepDataDTO + "]";
	}
	
	
	
	
}
