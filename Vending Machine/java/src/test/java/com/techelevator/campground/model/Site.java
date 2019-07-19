package com.techelevator.campground.model;

public class Site {

	private int siteId;
	private int campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private boolean acessible;
	private int maxRVLength;
	private boolean utilities;

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getCampgroundId() {
		return campgroundId;
	}

	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}

	public int getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}

	public int getMaxOccupancy() {
		return maxOccupancy;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public boolean isAcessible() {
		return acessible;
	}

	public void setAcessible(boolean acessible) {
		this.acessible = acessible;
	}

	public int getMaxRVLength() {
		return maxRVLength;
	}

	public void setMaxRVLength(int maxRVLength) {
		this.maxRVLength = maxRVLength;
	}

	public boolean isUtilities() {
		return utilities;
	}

	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
}
