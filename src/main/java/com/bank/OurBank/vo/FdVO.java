package com.bank.OurBank.vo;

public class FdVO  {

	
	
	private int durationmonth;
	
	private long amount;
	
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
		
		this.amount=amount;
		
	}
	
	
	@Override
	public String toString() {
		return "FD [ durationmonth=" + durationmonth + ", amount=" + amount+ "]";
	}
	
	
}
