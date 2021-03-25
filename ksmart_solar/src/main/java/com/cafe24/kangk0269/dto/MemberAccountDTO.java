package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberAccountDTO {

	private int mAccountIdx;
	private String mId;	
	private String mAccountNumber;	
	private String mAccountName;
	private String mAccountBank;	
	private String mAccountRegDate;	
	private String mAccountCheck;
}
