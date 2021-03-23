package com.cafe24.kangk0269.dto;

public class MemberDTO {
	private String mId;
	private String mPw;
	private String mName;
	private int mLevel;
	private String mZipcode;
	private String mAddr;
	private String mDetailAddr;
	private String mPhone;
	private String mEmail;
	private String mPhoto;
	private String mSubDate;
	private String mSubDateModify;
	private String mRestCheck;
	private String mRevokeCheck;
	private String mRevokeDelayCheck;
	private String mStateModifyDate;
	//권한처리를 위한 필드
	private String mLevelName;
	public String getmLevelName() {
		return mLevelName;
	}
	public void setmLevelName(String mLevelName) {
		this.mLevelName = mLevelName;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getmLevel() {
		return mLevel;
	}
	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public String getmZipcode() {
		return mZipcode;
	}
	public void setmZipcode(String mZipcode) {
		this.mZipcode = mZipcode;
	}
	public String getmAddr() {
		return mAddr;
	}
	public void setmAddr(String mAddr) {
		this.mAddr = mAddr;
	}
	public String getmDetailAddr() {
		return mDetailAddr;
	}
	public void setmDetailAddr(String mDetailAddr) {
		this.mDetailAddr = mDetailAddr;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmPhoto() {
		return mPhoto;
	}
	public void setmPhoto(String mPhoto) {
		this.mPhoto = mPhoto;
	}
	public String getmSubDate() {
		return mSubDate;
	}
	public void setmSubDate(String mSubDate) {
		this.mSubDate = mSubDate;
	}
	public String getmSubDateModify() {
		return mSubDateModify;
	}
	public void setmSubDateModify(String mSubDateModify) {
		this.mSubDateModify = mSubDateModify;
	}
	public String getmRestCheck() {
		return mRestCheck;
	}
	public void setmRestCheck(String mRestCheck) {
		this.mRestCheck = mRestCheck;
	}
	public String getmRevokeCheck() {
		return mRevokeCheck;
	}
	public void setmRevokeCheck(String mRevokeCheck) {
		this.mRevokeCheck = mRevokeCheck;
	}
	public String getmRevokeDelayCheck() {
		return mRevokeDelayCheck;
	}
	public void setmRevokeDelayCheck(String mRevokeDelayCheck) {
		this.mRevokeDelayCheck = mRevokeDelayCheck;
	}
	public String getmStateModifyDate() {
		return mStateModifyDate;
	}
	public void setmStateModifyDate(String mStateModifyDate) {
		this.mStateModifyDate = mStateModifyDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberDTO [mId=");
		builder.append(mId);
		builder.append(", mPw=");
		builder.append(mPw);
		builder.append(", mName=");
		builder.append(mName);
		builder.append(", mLevel=");
		builder.append(mLevel);
		builder.append(", mZipcode=");
		builder.append(mZipcode);
		builder.append(", mAddr=");
		builder.append(mAddr);
		builder.append(", mDetailAddr=");
		builder.append(mDetailAddr);
		builder.append(", mPhone=");
		builder.append(mPhone);
		builder.append(", mEmail=");
		builder.append(mEmail);
		builder.append(", mPhoto=");
		builder.append(mPhoto);
		builder.append(", mSubDate=");
		builder.append(mSubDate);
		builder.append(", mSubDateModify=");
		builder.append(mSubDateModify);
		builder.append(", mRestCheck=");
		builder.append(mRestCheck);
		builder.append(", mRevokeCheck=");
		builder.append(mRevokeCheck);
		builder.append(", mRevokeDelayCheck=");
		builder.append(mRevokeDelayCheck);
		builder.append(", mStateModifyDate=");
		builder.append(mStateModifyDate);
		builder.append("]");
		return builder.toString();
	}
}
