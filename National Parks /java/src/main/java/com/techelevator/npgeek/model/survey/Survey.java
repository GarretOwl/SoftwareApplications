package com.techelevator.npgeek.model.survey;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	private int surveyId;
	
	private String parkCode;
	
	@NotBlank(message="Email is required") @Email(message="Not a valid Email address")
	private String emailAddress;
	
	@NotBlank(message="State is required") 
	private String state;
	
	@NotBlank(message="Activity Level is required")
	private String activityLevel;
	
	private String parkImage;
	
	
	/**
	 * @return the parkImage
	 */
	public String getParkImage() {
		return parkImage;
	}
	/**
	 * @param parkImage the parkImage to set
	 */
	public void setParkImage(String parkImage) {
		this.parkImage = parkImage;
	}
	/**
	 * @return the surveyId
	 */
	public int getSurveyId() {
		return surveyId;
	}
	/**
	 * @param surveyId the surveyId to set
	 */
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	/**
	 * @return the parkCode
	 */
	public String getParkCode() {
		return parkCode;
	}
	/**
	 * @param parkCode the parkCode to set
	 */
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the activityLevel
	 */
	public String getActivityLevel() {
		return activityLevel;
	}
	/**
	 * @param activityLevel the activityLevel to set
	 */
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
}
