package com.techelevator.npgeek.model.survey;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addSurvey(Survey survey) {
		
		//Survey survey = null;

		
		String SQLString = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(SQLString, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		
		
		
	}

	@Override
	public LinkedHashMap<String, Integer> getSurveysWithOneCountMin() {
		
		LinkedHashMap<String, Integer>surveyList = new LinkedHashMap<>();
		
		SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT survey_result.parkcode, park.parkname, COUNT(survey_result.parkcode) AS parkcount\n" + 
				"FROM survey_result\n" + 
				"JOIN park ON park.parkcode = survey_result.parkcode\n" + 
				"GROUP BY survey_result.parkcode, park.parkname\n" + 
				"ORDER BY parkcount DESC, survey_result.parkcode ASC");
		
		while (result.next()) {

		//	surveyList.add(mapRowSetToSurvey(result));
			surveyList.put(result.getString("parkname"), result.getInt("parkcount"));

		}

		return surveyList;
	}

	
	
	
	
	
	
	
	
	private Survey mapRowSetToSurvey(SqlRowSet results) {
		Survey survey = new Survey();
		survey.setSurveyId(results.getInt("surveyid"));
		survey.setParkCode(results.getString("parkcode"));
		survey.setEmailAddress(results.getString("emailaddress"));
		survey.setState(results.getString("state"));
		survey.setActivityLevel(results.getString("activitylevel"));
		survey.setParkImage(results.getString("parkcode").toLowerCase());


		return survey;
	}
	
	
	
}
