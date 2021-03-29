package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NoticeDTO {
	private int noticeIdx;
	private String notSubject;
	private String notContents;
	private String mId;
	private String notRegDate;
	private int notViews;	
	private String notTemp;
}
	