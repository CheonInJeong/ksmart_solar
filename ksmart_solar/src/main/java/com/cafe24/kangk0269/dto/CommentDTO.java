package com.cafe24.kangk0269.dto;

public class CommentDTO {
	
	private int	cmtIdx;
	private int bIdx;
	private String cmtClass;
	private String mId;
	private String targetId;
	private String cmtReply;
	private String cmtSecret;
	private String cmtComment;
	private String cmtRegDate;
	private String cmtUpdateDate;
	private String cmtDeleteYn;
	private String cmtGroupCode;
	
	public int getCmtIdx() {
		return cmtIdx;
	}
	public void setCmtIdx(int cmtIdx) {
		this.cmtIdx = cmtIdx;
	}
	public int getbIdx() {
		return bIdx;
	}
	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}
	public String getCmtClass() {
		return cmtClass;
	}
	public void setCmtClass(String cmtClass) {
		this.cmtClass = cmtClass;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
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
	public String getCmtDeleteYn() {
		return cmtDeleteYn;
	}
	public void setCmtDeleteYn(String cmtDeleteYn) {
		this.cmtDeleteYn = cmtDeleteYn;
	}
	public String getCmtGroupCode() {
		return cmtGroupCode;
	}
	public void setCmtGroupCode(String cmtGroupCode) {
		this.cmtGroupCode = cmtGroupCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentDTO [cmtIdx=");
		builder.append(cmtIdx);
		builder.append(", bIdx=");
		builder.append(bIdx);
		builder.append(", cmtClass=");
		builder.append(cmtClass);
		builder.append(", mId=");
		builder.append(mId);
		builder.append(", targetId=");
		builder.append(targetId);
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
		builder.append(", cmtDeleteYn=");
		builder.append(cmtDeleteYn);
		builder.append(", cmtGroupCode=");
		builder.append(cmtGroupCode);
		builder.append("]");
		return builder.toString();
	}
	
}
