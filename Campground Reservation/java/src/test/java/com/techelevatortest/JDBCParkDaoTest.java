package com.techelevatortest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.JDBCParkDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;

public class JDBCParkDaoTest extends DAOIntegrationTest{
	JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
	String tableCount = "SELECT COUNT(*) FROM park";
	private ParkDAO dao = new JDBCParkDAO(this.getDataSource());
	
	@Test
	public void testgetAllParks() {
		SqlRowSet results = jdbcTemplate.queryForRowSet(tableCount);
		int count = 0;
		while(results.next()) {
			count = results.getInt(1);
		}
		List<Park> parks = dao.getAllParks();
		
		assertNotNull(parks);
		assertEquals(count, parks.size());
	}
	@Test
	public void testgetParkById() {
		Park park = dao.getParkById(1);
		
		assertNotNull(park);
		assertEquals("Acadia", park.getParkName());
	}
}
