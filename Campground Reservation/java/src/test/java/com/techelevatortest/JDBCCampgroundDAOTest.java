package com.techelevatortest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.JDBCCampgroundDAO;

public class JDBCCampgroundDAOTest extends DAOIntegrationTest{
	JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
	private CampgroundDAO dao = new JDBCCampgroundDAO(this.getDataSource());
	
	@Test
	public void testgetAllCampgroundsByParkId() {
		List<Campground> campGrounds = dao.getCampgroundByParkId(1);
		
		assertNotNull(campGrounds);
		assertEquals(3, campGrounds.size());
	}
	@Test
	public void testgetAllCampgrounds() {
		List<Campground> campGrounds = dao.getAllCampgrounds();
		
		assertNotNull(campGrounds);
		assertEquals(7, campGrounds.size());
		int count = 0;
		for (int i = 0; i < campGrounds.size(); i++) {
			count += 1;
			System.out.println(count + ". " + campGrounds.get(i).getCampgroundName());
		}
	}
}