package com.cafe24.kangk0269.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

	private int boardIdx;
	private String title ;
	private String contents;
	private int hitCnt;
	private LocalDateTime createdDatetime;
	private String creatorId;
	private LocalDateTime updatedDatetime;
	private String updaterId;
	private List<BoardFileDTO> fileList;
	
	
	public List<BoardFileDTO> getFileList() {
		return fileList;
	}
	public void setFileList(List<BoardFileDTO> fileList) {
		this.fileList = fileList;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
	}
	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public LocalDateTime getUpdatedDatetime() {
		return updatedDatetime;
	}
	public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	public String getUpdaterId() {
		return updaterId;
	}
	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardDto [boardIdx=");
		builder.append(boardIdx);
		builder.append(", title=");
		builder.append(title);
		builder.append(", contents=");
		builder.append(contents);
		builder.append(", hitCnt=");
		builder.append(hitCnt);
		builder.append(", createdDatetime=");
		builder.append(createdDatetime);
		builder.append(", creatorId=");
		builder.append(creatorId);
		builder.append(", updatedDatetime=");
		builder.append(updatedDatetime);
		builder.append(", updaterId=");
		builder.append(updaterId);
		builder.append(", fileList=");
		builder.append(fileList);
		builder.append("]");
		return builder.toString();
	}
	
	
}
