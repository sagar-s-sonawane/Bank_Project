package com.bank.OurBank.model;



import java.util.LinkedHashMap;
import java.util.Map;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="fd")
public class FD  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="fd_id")
	private long fdid;
	@Column(name="durationIn_month",nullable=false)
	private int durationmonth;
	@Column(name="amount",nullable=false,updatable=true,insertable=true)
	private long amount;
	@Column(name="interest",updatable=true)
	private double totalInterest;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="accountdetails_id",updatable=true)
	private AccountDetails accountDetails;
	@Transient
	private long accountBalance;
	@Transient
	private String message;
	
	
	final static Map<Integer,Double>interest=new LinkedHashMap<>();
	static {
		interest.put(12,7.3);
		interest.put(24,8.6);
		interest.put(36,9.8);
		interest.put(48,10.9);
	}
	
	
	public FD(){}
	public FD(String message) {this.message=message;}
	public FD(long fdid, int durationmonth, long amount) {
		super();
		this.fdid = fdid;
		this.durationmonth = durationmonth;
		this.amount = amount;
	}
	
	public void setTotalInterest() {
		if(getDurationmonth()==12) {
			this.totalInterest=interest.get(12);
		}
		if(getDurationmonth()==24) {
			this.totalInterest=interest.get(24);
		}
		if(getDurationmonth()==36) {
			this.totalInterest=interest.get(36);
		}
		if(getDurationmonth()==48) {
			this.totalInterest=interest.get(48);
		}
	}
	public double getTotalInterest() {
		return totalInterest;
	}
	public long getFdid() {
		return fdid;
	}
	public void setFdid(long fdid) {
		this.fdid = fdid;
	}
	public int getDurationmonth() {
		return durationmonth;
	}
	public void setDurationmonth(int durationmonth) {
		this.durationmonth = durationmonth;
	}
	public long getAmount() {
		return this.amount;
	}
	public void setAmount(long amount) {
		if(this.accountBalance>=amount) {
			this.amount=amount;
		}
		else {
			System.out.println(this.accountBalance);
			
		}
	}
	
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public AccountDetails getAccountDetails() {
		return this.accountDetails;
	}
	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		return "FD [fdid=" + fdid + ", durationmonth=" + durationmonth + ", amount=" + amount + ", accountDetails="
				+ accountDetails + "]";
	}
	
	
}
