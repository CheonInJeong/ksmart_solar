package com.cafe24.kangk0269.dto;

public class FileDTO {
	private String fileIdx;
	private String fileName; 
	private String fileSaveName; 
	private String mId;
	private String fileRegDate;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDTO [fileIdx=");
		builder.append(fileIdx);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", fileSaveName=");
		builder.append(fileSaveName);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", fileRegDate=");
		builder.append(fileRegDate);
		builder.append("]");
		return builder.toString();
	}
	
	public String getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(String fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSaveName() {
		return fileSaveName;
	}
	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getFileRegDate() {
		return fileRegDate;
	}
	public void setFileRegDate(String fileRegDate) {
		this.fileRegDate = fileRegDate;
	}
	
	
	
}
