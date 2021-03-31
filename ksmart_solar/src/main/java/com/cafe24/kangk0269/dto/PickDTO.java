package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PickDTO {
	private int pIdx;
	private String bPlComponentCode;	
	private String bPlComponentSub;
	private String mId;
	private String pRegDate;	
	private String bEndDate;
	private String bTypeCode;
}