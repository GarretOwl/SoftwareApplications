package com.techelevator.npgeek.model.survey;

import java.util.LinkedHashMap;

public interface SurveyDao {

	public void addSurvey (Survey survey);
	/* takes input from user survey and inserts into database */
	
	public LinkedHashMap <String, Integer> getSurveysWithOneCountMin ();
	/* returns all surveys from database from parks with at least one survey */
}
