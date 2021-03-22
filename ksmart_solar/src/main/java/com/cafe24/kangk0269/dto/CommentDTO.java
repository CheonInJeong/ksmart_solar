package com.cafe24.kangk0269.dto;

public class CommentDTO {
	private int cmtIdx;
	private int cmtBoardIdx;
	private String mId;
	private String cmtSolt;
	private String cmtReply;
	private String cmtSecret;
	private String cmtComment;
	private String cmtRegDate;
	private String cmtUpdateDate;
	private String cmtDeleteCheck;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentDTO [cmtIdx=");
		builder.append(cmtIdx);
		builder.append(", cmtBoardIdx=");
		builder.append(cmtBoardIdx);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", cmtSolt=");
		builder.append(cmtSolt);
		builder.append(", cmtReply=");
		builder.append(cmtReply);
		builder.append(", cmtSecret=");
		builder.append(cmtSecret);
		builder.append(", cmtComment=");
		builder.append(cmtComment);
		builder.append(", cmtRegDate=");
		builder.append(cmtRegDate);
		builder.append(", cmtUpdateDate=");
		builder.append(cmtUpdateDate);
		builder.append(", cmtDeleteCheck=");
		builder.append(cmtDeleteCheck);
		builder.append("]");
		return builder.toString();
	}
	public int getCmtIdx() {
		return cmtIdx;
	}
	public void setCmtIdx(int cmtIdx) {
		this.cmtIdx = cmtIdx;
	}
	public int getCmtBoardIdx() {
		return cmtBoardIdx;
	}
	public void setCmtBoardIdx(int cmtBoardIdx) {
		this.cmtBoardIdx = cmtBoardIdx;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getCmtSolt() {
		return cmtSolt;
	}
	public void setCmtSolt(String cmtSolt) {
		this.cmtSolt = cmtSolt;
	}
	public String getCmtReply() {
		return cmtReply;
	}
	public void setCmtReply(String cmtReply) {
		this.cmtReply = cmtReply;
	}
	public String getCmtSecret() {
		return cmtSecret;
	}
	public void setCmtSecret(String cmtSecret) {
		this.cmtSecret = cmtSecret;
	}
	public String getCmtComment() {
		return cmtComment;
	}
	public void setCmtComment(String cmtComment) {
		this.cmtComment = cmtComment;
	}
	public String getCmtRegDate() {
		return cmtRegDate;
	}
	public void setCmtRegDate(String cmtRegDate) {
		this.cmtRegDate = cmtRegDate;
	}
	public String getCmtUpdateDate() {
		return cmtUpdateDate;
	}
	public void setCmtUpdateDate(String cmtUpdateDate) {
		this.cmtUpdateDate = cmtUpdateDate;
	}
	public String getCmtDeleteCheck() {
		return cmtDeleteCheck;
	}
	public void setCmtDeleteCheck(String cmtDeleteCheck) {
		this.cmtDeleteCheck = cmtDeleteCheck;
	}
	

}
