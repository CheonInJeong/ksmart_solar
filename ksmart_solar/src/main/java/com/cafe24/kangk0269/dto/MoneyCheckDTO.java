package com.cafe24.kangk0269.dto;

public class MoneyCheckDTO {

	private String Code;
	private String nCode;
	private String nTitle;
	private String Id;
	private long money;
	private String iOut;
	private String inoutCheck;
	private String inoutDate;
	private String bankName;
	private String accountNumber;
	private String accountName;
	private String moCode;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getnCode() {
		return nCode;
	}
	public void setnCode(String nCode) {
		this.nCode = nCode;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public String getiOut() {
		return iOut;
	}
	public void setiOut(String iOut) {
		this.iOut = iOut;
	}
	public String getInoutCheck() {
		return inoutCheck;
	}
	public void setInoutCheck(String inoutCheck) {
		this.inoutCheck = inoutCheck;
	}
	public String getInoutDate() {
		return inoutDate;
	}
	public void setInoutDate(String inoutDate) {
		this.inoutDate = inoutDate;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getMoCode() {
		return moCode;
	}
	public void setMoCode(String moCode) {
		this.moCode = moCode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MoneyCheckDTO [Code=");
		builder.append(Code);
		builder.append(", nCode=");
		builder.append(nCode);
		builder.append(", nTitle=");
		builder.append(nTitle);
		builder.append(", Id=");
		builder.append(Id);
		builder.append(", money=");
		builder.append(money);
		builder.append(", iOut=");
		builder.append(iOut);
		builder.append(", inoutCheck=");
		builder.append(inoutCheck);
		builder.append(", inoutDate=");
		builder.append(inoutDate);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", moCode=");
		builder.append(moCode);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
