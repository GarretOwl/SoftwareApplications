package com.techelevatortest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.campground.model.JDBCParkDAO;
import com.techelevator.campground.model.JDBCSiteDAO;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;

public class JDBCSiteDAOTest extends DAOIntegrationTest {

	JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
	private SiteDAO dao = new JDBCSiteDAO(this.getDataSource());

	@Test
	public void testsearchReservations() {
		LocalDate fromDate = LocalDate.of(2019, Month.JUNE, 17);
		LocalDate toDate = LocalDate.of(2019, Month.SEPTEMBER, 19);
		List<Site> availibleSites = dao.searchReservations(1, fromDate, toDate);

		assertNotNull(availibleSites);
		assertEquals(2, availibleSites.size());
	}
}
