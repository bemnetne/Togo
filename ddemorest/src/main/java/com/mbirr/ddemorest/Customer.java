package com.mbirr.ddemorest;

public class Customer {
	
	private int phone;
	private int timer;
	private int couter;
	
	public Customer(int phone, int timer, int couter) {
		this.phone = phone;
		this.timer = timer;
		this.couter = couter;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public int getCouter() {
		return couter;
	}
	public void setCouter(int couter) {
		this.couter = couter;
	}

}
