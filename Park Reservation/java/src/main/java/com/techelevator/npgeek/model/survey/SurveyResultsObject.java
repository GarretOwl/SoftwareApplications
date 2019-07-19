package com.techelevator.npgeek.model.survey;

import java.util.List;

import com.techelevator.npgeek.model.park.Park;

public class SurveyResultsObject {
	
	
	private List<Park> topParkList;	
	private List<Survey> topSurveyList;
	
	
	
	/**
	 * @return the topParkList
	 */
	public List<Park> getTopParkList() {
		return topParkList;
	}
	/**
	 * @param topParkList the topParkList to set
	 */
	public void setTopParkList(List<Park> topParkList) {
		this.topParkList = topParkList;
	}
	/**
	 * @return the topSurveyList
	 */
	public List<Survey> getTopSurveyList() {
		return topSurveyList;
	}
	/**
	 * @param topSurveyList the topSurveyList to set
	 */
	public void setTopSurveyList(List<Survey> topSurveyList) {
		this.topSurveyList = topSurveyList;
	}
	
	

}
