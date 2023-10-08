package com.bank.OurBank.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Account_Details")
public class AccountDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AccountType_ID")
	private long uid;
	
	@Column(name = "AccountType_Name", nullable = false)
	private String accounttype;
	
	@Column(name = "Account_Number", nullable = false, unique = true)
	private long accountnumber;
	
	@Column(name = "IFSC_Code", nullable = false)
	private String ifsc;
	
	@Column(name = "CIF_Number", nullable = false, unique = true)
	private long cifnumber;
	
	@Column(name = "Balance")
	private long balance;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnoreProperties("accountDetails")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "accountDetails")
	private List<FD> fd;
	
	@Transient
	private String message;

	public AccountDetails() {
	}

	public AccountDetails(String message) {
		this.message = message;
	}

	public AccountDetails(long uid, String accounttype, long accountnumber, String ifsc, long cifnumber, long balance,
			User user) {

		this.balance = balance;
		this.uid = uid;
		this.accounttype = accounttype;
		this.accountnumber = accountnumber;
		this.ifsc = ifsc;
		this.cifnumber = cifnumber;
		this.user = user;
	}

	public void removelAccountDetails(FD fd) {
		this.fd.remove(fd);
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long baid) {
		this.uid = baid;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public List<FD> getFd() {
		return fd;
	}

	public void setFd(List<FD> fd) {
		this.fd = fd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AccountDetails [uid=" + uid + ", accounttype=" + accounttype + ", accountnumber=" + accountnumber
				+ ", ifsc=" + ifsc + ", cifnumber=" + cifnumber + ", balance=" + balance + ", user=" + user + ", fd="
				+ fd + "]";
	}

	public void receiveFund(long fund) {
		this.balance = this.balance + fund;
	}

	public void sendFund(long fund) {
		this.balance = this.balance - fund;
	}

}
