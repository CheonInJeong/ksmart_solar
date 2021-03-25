package com.cafe24.kangk0269.dto;

public class BoardFileDTO {

	
	private int idx;
	private int boardIdx;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
	private String creatorId;
	private String createdDatetime;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardFileDTO [idx=");
		builder.append(idx);
		builder.append(", boardIdx=");
		builder.append(boardIdx);
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
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
