package com.techelevator;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.park.JdbcParkDao;
import com.techelevator.npgeek.model.park.Park;

public class JDBCParkDaoTest extends DAOIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	JdbcParkDao jdbcParkDao = new JdbcParkDao(dataSource);

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
	public void getAllParksTest() {

		List<Park> listOfParks = jdbcParkDao.getAllParks();
		Assert.assertNotNull(listOfParks);
		Assert.assertTrue(listOfParks.size() > 0);
	}

	@Test
	public void getParkByParkCodeTest() {

		Park jamesPark = jdbcParkDao.getParkByParkCode("JAMES");
		Assert.assertNotNull(jamesPark);
		Assert.assertEquals("JAMES", jamesPark.getParkCode());
		Assert.assertEquals("James Park", jamesPark.getParkName());
		Assert.assertEquals("I made this park", jamesPark.getQuote());
		Assert.assertEquals(15, jamesPark.getAcreage());
		Assert.assertEquals(100, jamesPark.getAnnualVisitorCount());
		Assert.assertEquals("desert", jamesPark.getClimate());
		Assert.assertEquals(2, jamesPark.getNumberOfAnimalSpecies());
		Assert.assertEquals(1, jamesPark.getNumberOfCampsites());
		Assert.assertEquals("Wyoming", jamesPark.getState());
		Assert.assertEquals(2019, jamesPark.getYearFounded());

	}

	public void addPark(Park park) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String SQLString = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(SQLString, park.getParkCode(), park.getParkName(), park.getState(), park.getAcreage(),
				park.getElevationInFeet(), park.getMilesOfTrail(), park.getNumberOfCampsites(), park.getClimate(),
				park.getYearFounded(), park.getAnnualVisitorCount(), park.getQuote(), park.getQuoteSource(),
				park.getDescription(), park.getEntryFee(), park.getNumberOfAnimalSpecies());

	}
}
