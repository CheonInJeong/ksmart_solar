package com.cafe24.kangk0269.dto;

public class MemberKakao {
	private String mId;
	private String mPw;
	private String kakaoId;
	private String nickName;
	private String thumbnailImage;
	private String kakaoEmail;
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getKakaoId() {
		return kakaoId;
	}
	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getKakaoEmail() {
		return kakaoEmail;
	}
	public void setKakaoEmail(String kakaoEmail) {
		this.kakaoEmail = kakaoEmail;
	}
	public String getThumbnailImage() {
		return thumbnailImage;
	}
	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	@Override
	public String toString() {
		return "MemberKakao [kakaoId=" + kakaoId + ", nickName=" + nickName + ", thumbnailImage=" + thumbnailImage
				+ ", kakaoEmail=" + kakaoEmail + "]";
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	
	
}
