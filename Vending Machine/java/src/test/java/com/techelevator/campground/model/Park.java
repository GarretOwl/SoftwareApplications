package com.techelevator.campground.model;

import java.time.LocalDate;

public class Park {

	private int parkId;
	private String parkName;
	private String parkLocation;
	private LocalDate establishedDate;
	private int parkArea;
	private int annualVisitors;
	private String parkDescription;

	public int getParkId() {
		return parkId;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getParkLocation() {
		return parkLocation;
	}

	public void setParkLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}

	public LocalDate getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}

	public int getParkArea() {
		return parkArea;
	}

	public void setParkArea(int parkArea) {
		this.parkArea = parkArea;
	}

	public int getAnnualVisitors() {
		return annualVisitors;
	}

	public void setAnnualVisitors(int annualVisitors) {
		this.annualVisitors = annualVisitors;
	}

	public String getParkDescription() {
		return parkDescription;
	}

	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}

}
