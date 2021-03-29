package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class BoardQnaDTO {
	private int bQnaIdx;
	private String bQnaSubject;
	private String bQnaContents;
	private String mId;
	private String bQnaRegDate;
	private int bQnaViews;
	private String bQnaOpen;
	private String bQnaPassword;
	private int bQnaRefCode;
	private int bQnaReLev;
	private int bQnaReSeq;
	private String bQnaTemp;
}