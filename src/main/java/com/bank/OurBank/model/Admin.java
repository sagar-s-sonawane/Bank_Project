package com.bank.OurBank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AdminId")
	private long aid;
	@Column(name="Password",nullable=false)
	private String password;
	@Column(name="Admin_Name",nullable=false,unique=true)
	private String adminname;
	
	@Transient
	private boolean isLogin=false;
	
	public Admin() {
	}
	
		
	public boolean isLogin() {
		return isLogin;
	}



	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}



	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", password=" + password + ", adminname=" + adminname + "]";
	}
}
