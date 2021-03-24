package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BusinessPlantDTO {
	private String bzPlCode;
	private String mId;
	private String bzPlCheck;
	private String bzPlName;	
	private String bzPlZipcode;	
	private String bzPlAddr;	
	private String bzPlDetailAddr;	
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
