package com.techelevator.npgeek.model.survey;

import java.util.LinkedHashMap;

public interface SurveyDao {

	
	public void addSurvey (Survey survey);
	
	
	public LinkedHashMap <String, Integer> getSurveysWithOneCountMin ();
}
