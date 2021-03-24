package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TradeDepositOutDTO {
	private String trDepCode;
	private String bCode;
	private String mId;
	private int bDeposit;
	private int trDepDeposit;
	private String mAccountBankName;
	private String mAccountNumber;
	private String trDepAccountCheck;
	private String mAccountName;
	private String trDepDate;
	private String trDepCheck;
	private String trDepWdDate;
	private String bMoCode;
	
}
