package com.cafe24.kangk0269.dto;


public class BusinessPlantDTO {
	private String bzPlCode;
	private String bzCode;
	private String mId;
	private String bzPlCheck;
	private String bzPlDelete;
	private String bzPlName;	
	private String bzPlZipcode;	
	private String bzPlAddr;	
	private String bzPlDetailAddr;	
	private int bzPlAddrCode;
	private String bzPlPhoto;
	private int bzPlPower;
	private String bzPlHardware;	
	private int bzPlArea;	
	private int bzPlInvPower;	
	private int bzPlInvCount;	
	private String bzPlInvMaker;	
	private int bzPlRec;
	private String bzPlRegDate;	
	private String bzPlModDate;	
	private PlantDepreciationDTO plantDepreciationDTO;
	private PlantDepDataDTO plantDepDataDTO;
	
	private int residualValue;
	
	
	
	
	public int getResidualValue() {
		return residualValue;
	}
	public void setResidualValue(int residualValue) {
		this.residualValue = residualValue;
	}
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
	public String getBzPlDetailAddr() {
		return bzPlDetailAddr;
	}
	public void setBzPlDetailAddr(String bzPlDetailAddr) {
		this.bzPlDetailAddr = bzPlDetailAddr;
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

	public String getBzCode() {
		return bzCode;
	}
	public void setBzCode(String bzCode) {
		this.bzCode = bzCode;
	}
	public String getBzPlRegDate() {
		return bzPlRegDate;
	}
	public void setBzPlRegDate(String bzPlRegDate) {
		this.bzPlRegDate = bzPlRegDate;
	}
	public String getBzPlModDate() {
		return bzPlModDate;
	}
	public void setBzPlModDate(String bzPlModDate) {
		this.bzPlModDate = bzPlModDate;
	}
	public int getBzPlAddrCode() {
		return bzPlAddrCode;
	}
	public void setBzPlAddrCode(int bzPlAddrCode) {
		this.bzPlAddrCode = bzPlAddrCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessPlantDTO [bzPlCode=");
		builder.append(bzPlCode);
		builder.append(", bzCode=");
		builder.append(bzCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", bzPlCheck=");
		builder.append(bzPlCheck);
		builder.append(", bzPlName=");
		builder.append(bzPlName);
		builder.append(", bzPlZipcode=");
		builder.append(bzPlZipcode);
		builder.append(", bzPlAddr=");
		builder.append(bzPlAddr);
		builder.append(", bzPlDetailAddr=");
		builder.append(bzPlDetailAddr);
		builder.append(", bzPlAddrCode=");
		builder.append(bzPlAddrCode);
		builder.append(", bzPlPhoto=");
		builder.append(bzPlPhoto);
		builder.append(", bzPlPower=");
		builder.append(bzPlPower);
		builder.append(", bzPlHardware=");
		builder.append(bzPlHardware);
		builder.append(", bzPlArea=");
		builder.append(bzPlArea);
		builder.append(", bzPlInvPower=");
		builder.append(bzPlInvPower);
		builder.append(", bzPlInvCount=");
		builder.append(bzPlInvCount);
		builder.append(", bzPlInvMaker=");
		builder.append(bzPlInvMaker);
		builder.append(", bzPlRec=");
		builder.append(bzPlRec);
		builder.append(", bzPlRegDate=");
		builder.append(bzPlRegDate);
		builder.append(", bzPlModDate=");
		builder.append(bzPlModDate);
		builder.append(", plantDepreciationDTO=");
		builder.append(plantDepreciationDTO);
		builder.append(", plantDepDataDTO=");
		builder.append(plantDepDataDTO);
		builder.append(", residualValue=");
		builder.append(residualValue);
		builder.append("]");
		return builder.toString();
	}
	public String getBzPlDelete() {
		return bzPlDelete;
	}
	public void setBzPlDelete(String bzPlDelete) {
		this.bzPlDelete = bzPlDelete;
	}
	
	
	
	
	
}
