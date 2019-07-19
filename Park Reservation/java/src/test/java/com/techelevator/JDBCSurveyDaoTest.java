package com.techelevator;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.park.Park;
import com.techelevator.npgeek.model.survey.JdbcSurveyDao;
import com.techelevator.npgeek.model.survey.Survey;

public class JDBCSurveyDaoTest extends DAOIntegrationTest {

private static SingleConnectionDataSource dataSource;
JdbcSurveyDao jdbcSurveyDao = new JdbcSurveyDao(dataSource);


@BeforeClass
public static void setupDataSource() {
    dataSource = new SingleConnectionDataSource();
    dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
    dataSource.setUsername("postgres");
    dataSource.setPassword("postgres1");
    dataSource.setAutoCommit(false);
    
}

@AfterClass
public static void closeDataSource() throws SQLException {
    dataSource.destroy();
}

@Before
public void setUp() throws Exception {
    Park park = new Park();
    park.setAcreage(15);
    park.setAnnualVisitorCount(100);
    park.setClimate("desert");
    park.setDescription("A great park");
    park.setElevationInFeet(50);
    park.setEntryFee(5);
    park.setMilesOfTrail(1000);
    park.setNumberOfAnimalSpecies(2);
    park.setNumberOfCampsites(1);
    park.setParkCode("JAMES");
    park.setParkName("James Park");
    park.setQuote("I made this park");
    park.setQuoteSource("James");
    park.setState("Wyoming");
    park.setYearFounded(2019);
    addPark(park);
}

@After
public void rollback() throws SQLException {
    dataSource.getConnection().rollback();
}

@Test
public void addSurveyTest() {
    Survey surveyJames = new Survey();
    surveyJames.setParkCode("JAMES");
    surveyJames.setEmailAddress("jamienowlworm@gmail.com");
    surveyJames.setState("Wyoming");
    surveyJames.setActivityLevel("sedentary");
    
    jdbcSurveyDao.addSurvey(surveyJames);
    
    LinkedHashMap<String, Integer> surveyList = jdbcSurveyDao.getSurveysWithOneCountMin();
    Assert.assertNotNull(surveyList);
    Assert.assertEquals(true, surveyList.containsKey("James Park"));
    Assert.assertTrue(surveyList.get("James Park") == 1);
    
}




public void addPark(Park park) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    String SQLString = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(SQLString, park.getParkCode(), park.getParkName(), park.getState(), park.getAcreage(), park.getElevationInFeet(), park.getMilesOfTrail(), park.getNumberOfCampsites(), park.getClimate(), park.getYearFounded(), park.getAnnualVisitorCount(), park.getQuote(), park.getQuoteSource(), park.getDescription(), park.getEntryFee(), park.getNumberOfAnimalSpecies());
    
}
}