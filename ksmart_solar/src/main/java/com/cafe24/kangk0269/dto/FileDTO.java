package com.cafe24.kangk0269.dto;

public class FileDTO {

	private String fileName; 
	private String fileRealName; 
	private String mId;
	private String fileRegDate;

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDTO [fileName=");
		builder.append(fileName);
		builder.append(", fileRealName=");
		builder.append(fileRealName);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", fileRegDate=");
		builder.append(fileRegDate);
		builder.append("]");
		return builder.toString();
	}

	
}
