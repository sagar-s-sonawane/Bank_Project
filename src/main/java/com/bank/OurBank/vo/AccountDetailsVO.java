package com.bank.OurBank.vo;

public class AccountDetailsVO {

	
	private long uid;
	private String accounttype;
	private long accountnumber;
	private String ifsc;
	private long cifnumber;
	private long balance;
	
	
	
	public AccountDetailsVO() {
	
	}

	

	public long getUid() {
		return uid;
	}



	public void setUid(long uid) {
		this.uid = uid;
	}



	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public long getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public long getCifnumber() {
		return cifnumber;
	}

	public void setCifnumber(long cifnumber) {
		this.cifnumber = cifnumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	

	@Override
	public String toString() {
		return "AccountDetails [ accounttype=" + accounttype + ", accountnumber=" + accountnumber
				+ ", ifsc=" + ifsc + ", cifnumber=" + cifnumber + ", balance=" + balance + "]";
	}
	
	public void receiveFund(long fund) {
		this.balance=this.balance+fund;
	}
	public void sendFund(long fund) {
		this.balance=this.balance-fund;
	}
	
	

	
	
	
	
}
