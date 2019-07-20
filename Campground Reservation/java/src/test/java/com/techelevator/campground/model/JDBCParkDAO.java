package com.techelevator.campground.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<>();
		String sqlGetAllParks = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park park = mapRowToPark(results);
			parks.add(park);
		}
		return parks;
	}

	@Override
	public Park mapRowToPark(SqlRowSet results) {
		Park park = new Park();
		park.setParkId(results.getInt("park_id"));
		park.setParkName(results.getString("name"));
		park.setParkLocation(results.getString("location"));
		park.setEstablishedDate(results.getDate("establish_date").toLocalDate());
		park.setParkArea(results.getInt("area"));
		park.setAnnualVisitors(results.getInt("visitors"));
		park.setParkDescription(results.getString("description"));
		return park;
	}
	@Override
	public Park getParkById(int parkId) {		
		Park park = new Park(); 
		String sqlGetPark = "SELECT park_id, name, location, establish_date, area, visitors, description\n" + 
				"FROM park\n" + 
				"WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPark, parkId);
		while (results.next()) {
			park = mapRowToPark(results);
		}
		return park;
	}
}