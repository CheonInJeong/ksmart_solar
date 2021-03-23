package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BidPlantDTO {

	private String bPlCode;
	private String mId;
	private String bPlTitle;
	private String bzPlCode;
	private String bPlContents;
	private int bPlPrice;
	private String bPlSpecs;
	private String bPlDocument;
	private int plResidual;	
	private int plDepPrice;
	private String plDepStartDate;
	private String plDepUsed;
	private String bPlStatus;
	private String bPlDateRequest;
	private String bPlDateBidding1;
	private String bPlDateBidding2;	
	private String bPlDateDecision1;
	private String bPlDateDecision2;	
	private String bPlGroupcode;
	private int bPlReCount;
	private String bPlConfirm;
	private String bPlConfirmStatus;
	private String bPlRejectReason;	
	private String bPlCancelReason;
	private String bPlCancelDate;
	private int acStatusCode;
	private int bPlNumberOfBidder;
	private BusinessPlantDTO businessPlantDTO;
	
	
}
