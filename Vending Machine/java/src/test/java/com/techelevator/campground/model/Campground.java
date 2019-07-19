package com.techelevator.campground.model;

import java.math.BigDecimal;

public class Campground {

	private int campgroundId;
	private int parkId;
	private String campgroundName;
	private int openFrom;
	private int openTo;
	private BigDecimal dailyFee;
	
	public int getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public String getCampgroundName() {
		return campgroundName;
	}
	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}
	
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	public int getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(int openFrom) {
		this.openFrom = openFrom;
	}
	public int getOpenTo() {
		return openTo;
	}
	public void setOpenTo(int openTo) {
		this.openTo = openTo;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
}
