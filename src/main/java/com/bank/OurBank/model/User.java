package com.bank.OurBank.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Id")
	private long uid;
	@Column(name="First_Name",nullable=false)
	private String firstname;
	@Column(name="Last_Name",nullable=false)
	private String lastname;
	@Column(name="Aadhar_Number",nullable=false,unique=true)
	private long aadharnumber;
	@Column(name="Address",nullable=false)
	private String address;
	@Column(name="Mobile_Number",nullable=false,unique=true)
	private long mobilenumber;
	@Column(name="Email",nullable=false,unique=true)
	private String email;
	
	@JsonIgnoreProperties("user")
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true, mappedBy="user")
	
	private List<AccountDetails> list;
	@Transient
	private boolean isLogin=false;
	@Transient
	private String message;
	
	public void removelAccountDetails(AccountDetails accountDetails) {
		list.remove(accountDetails);
	}
	
	public User(String message) {this.message=message;}
	
	public User(long uid, String firstname, String lastname, long aadharnumber, String address, long mobilenumber,
			String email, List<AccountDetails> list) {
		super();
		this.uid = uid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.aadharnumber = aadharnumber;
		this.address = address;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.list = list;
	}
	public User() {
	
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public long getAadharnumber() {
		return aadharnumber;
	}
	public void setAadharnumber(long aadharnumber) {
		this.aadharnumber = aadharnumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public List<AccountDetails> getList() {
		return list;
	}
	public void setList(List<AccountDetails> list) {
		this.list = list;
	}
	
	public boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", firstname=" + firstname + ", lastname=" + lastname + ", aadharnumber="
				+ aadharnumber + ", address=" + address + ", mobilenumber=" + mobilenumber + ", email=" + email
				+ ", list=" + list + "]";
	}	
	
	
	
}
