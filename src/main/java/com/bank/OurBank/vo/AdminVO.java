package com.bank.OurBank.vo;

public class AdminVO {

	private long aid;

	private String password;
	
	private String adminname;
	
	public AdminVO() {
		super();
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
		return "Admin [ password=" + password + ", adminname=" + adminname + "]";
	}
}
