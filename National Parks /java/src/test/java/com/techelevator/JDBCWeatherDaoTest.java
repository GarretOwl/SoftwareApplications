package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.park.Park;
import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.weather.JdbcWeatherDao;
import com.techelevator.npgeek.model.weather.Weather;
import com.techelevator.npgeek.model.weather.WeatherDao;

public class JDBCWeatherDaoTest extends DAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	JdbcWeatherDao jdbcWeatherDao = new JdbcWeatherDao(dataSource);


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
		
		for (int i = 1; i<=5; i++) {
		Weather weather = new Weather();
		weather.setParkCode("JAMES");
		weather.setDayOfForecast(i);
		weather.setLow(70 + i);
		weather.setHigh(72 + i);
		weather.setForecast("thunderstorms");
		addWeather(weather);
		}

	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	
	@Test
	public void getWeatherByForeCastByParkCodeTest() {
		
		List<Weather> listOfWeather = jdbcWeatherDao.getWeatherForecastByParkCode("JAMES");
		Assert.assertEquals(5, listOfWeather.size());
		Assert.assertEquals("JAMES", listOfWeather.get(0).getParkCode());
		Assert.assertEquals(71, listOfWeather.get(0).getLow());
		Assert.assertEquals(73, listOfWeather.get(0).getHigh());
		Assert.assertEquals("thunderstorms", listOfWeather.get(0).getForecast());
		Assert.assertEquals(1, listOfWeather.get(0).getDayOfForecast());
		Assert.assertEquals(77, listOfWeather.get(4).getHigh());
	
	}

	
	public void addWeather(Weather weather) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String SQLString = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(SQLString, weather.getParkCode(), weather.getDayOfForecast(), weather.getLow(), weather.getHigh(), weather.getForecast());
		
	}
	
	public void addPark(Park park) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String SQLString = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(SQLString, park.getParkCode(), park.getParkName(), park.getState(), park.getAcreage(), park.getElevationInFeet(), park.getMilesOfTrail(), park.getNumberOfCampsites(), park.getClimate(), park.getYearFounded(), park.getAnnualVisitorCount(), park.getQuote(), park.getQuoteSource(), park.getDescription(), park.getEntryFee(), park.getNumberOfAnimalSpecies());
		
	}
}
