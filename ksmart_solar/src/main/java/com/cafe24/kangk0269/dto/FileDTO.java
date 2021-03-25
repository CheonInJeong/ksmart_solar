package com.cafe24.kangk0269.dto;

public class FileDTO {
	private int idx;
	private String relatedTableCode;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
	private String creatorId;
	private String createdDatetime;
	private String updatorId ;
	private String updatedDatetime;
	private int fileSortIdx;
	private String fileSortName;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getRelatedTableCode() {
		return relatedTableCode;
	}
	public void setRelatedTableCode(String relatedTableCode) {
		this.relatedTableCode = relatedTableCode;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getStoredFilePath() {
		return storedFilePath;
	}
	public void setStoredFilePath(String storedFilePath) {
		this.storedFilePath = storedFilePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdatorId() {
		return updatorId;
	}
	public void setUpdatorId(String updatorId) {
		this.updatorId = updatorId;
	}
	public String getUpdatedDatetime() {
		return updatedDatetime;
	}
	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	public int getFileSortIdx() {
		return fileSortIdx;
	}
	public void setFileSortIdx(int fileSortIdx) {
		this.fileSortIdx = fileSortIdx;
	}
	public String getFileSortName() {
		return fileSortName;
	}
	public void setFileSortName(String fileSortName) {
		this.fileSortName = fileSortName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDTO [idx=");
		builder.append(idx);
		builder.append(", relatedTableCode=");
		builder.append(relatedTableCode);
		builder.append(", originalFileName=");
		builder.append(originalFileName);
		builder.append(", storedFilePath=");
		builder.append(storedFilePath);
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", creatorId=");
		builder.append(creatorId);
		builder.append(", createdDatetime=");
		builder.append(createdDatetime);
		builder.append(", updatorId=");
		builder.append(updatorId);
		builder.append(", updatedDatetime=");
		builder.append(updatedDatetime);
		builder.append(", fileSortIdx=");
		builder.append(fileSortIdx);
		builder.append(", fileSortName=");
		builder.append(fileSortName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
