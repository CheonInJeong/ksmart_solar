package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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
	

	
	
	
}
