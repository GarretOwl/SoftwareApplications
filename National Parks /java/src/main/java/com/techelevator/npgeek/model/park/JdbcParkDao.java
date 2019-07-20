package com.techelevator.npgeek.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {

		List<Park> allParksList = new ArrayList<Park>();

		SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT * FROM park");
		while (result.next()) {

			allParksList.add(mapRowSetToPark(result));
	

		}

		return allParksList;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		Park park = null;

		SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT * FROM park WHERE parkcode = ?", parkCode);
		if (result.next()) {

			park = mapRowSetToPark(result);

		}

		return park;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private Park mapRowSetToPark(SqlRowSet results) {
		Park park = new Park();
		park.setParkCode(results.getString("parkcode"));
		park.setParkName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setAcreage(results.getInt("acreage"));
		park.setElevationInFeet(results.getInt("elevationinfeet"));
		park.setMilesOfTrail(results.getFloat("milesoftrail"));
		park.setNumberOfCampsites(results.getInt("numberofcampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getInt("yearfounded"));
		park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		park.setQuote(results.getString("inspirationalquote"));
		park.setQuoteSource(results.getString("inspirationalquotesource"));
		park.setDescription(results.getString("parkdescription"));
		park.setEntryFee(results.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		park.setParkImage(results.getString("parkcode").toLowerCase());

		return park;
	}
}
