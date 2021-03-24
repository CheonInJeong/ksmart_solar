package com.cafe24.kangk0269.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TradePaymentOutDTO {
	private String trPayoutCode;
	private String trPrCode;
	private String mId;
	private int trPayoutPrice;
	private double sCommissionRate;
	private int cTradeCm;
	private int trPayoutPriceReal;
	private String trPayoutBank;
	private String trPayoutAccount;
	private String trPayoutAccountCheck;
	private String trPayoutAccountName;
	private String trPayoutDate;	
	private String trPayoutCheck;	
	private String trPayoutWdDate;
	private String bMoCode;
	
}
