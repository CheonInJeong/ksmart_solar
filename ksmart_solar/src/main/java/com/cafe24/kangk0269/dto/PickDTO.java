package com.cafe24.kangk0269.dto;

public class PickDTO {
	private int pIdx;
	private String pCode;	
	private String mId;
	private String pRegDate;	
	private BidPlantDTO bidPlantDTO;
	private BidComponentDTO bidComponentDTO;
	public int getpIdx() {
		return pIdx;
	}
	public void setpIdx(int pIdx) {
		this.pIdx = pIdx;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getpRegDate() {
		return pRegDate;
	}
	public void setpRegDate(String pRegDate) {
		this.pRegDate = pRegDate;
	}
	public BidPlantDTO getBidPlantDTO() {
		return bidPlantDTO;
	}
	public void setBidPlantDTO(BidPlantDTO bidPlantDTO) {
		this.bidPlantDTO = bidPlantDTO;
	}
	public BidComponentDTO getBidComponentDTO() {
		return bidComponentDTO;
	}
	public void setBidComponentDTO(BidComponentDTO bidComponentDTO) {
		this.bidComponentDTO = bidComponentDTO;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PickDTO [pIdx=");
		builder.append(pIdx);
		builder.append(", pCode=");
		builder.append(pCode);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", pRegDate=");
		builder.append(pRegDate);
		builder.append(", bidPlantDTO=");
		builder.append(bidPlantDTO);
		builder.append(", bidComponentDTO=");
		builder.append(bidComponentDTO);
		builder.append("]");
		return builder.toString();
	}
	
	
}