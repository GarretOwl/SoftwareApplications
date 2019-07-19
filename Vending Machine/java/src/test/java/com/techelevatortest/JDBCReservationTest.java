package com.techelevatortest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.JDBCCampgroundDAO;
import com.techelevator.campground.model.JDBCReservationDAO;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;

public class JDBCReservationTest extends DAOIntegrationTest {

	JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
	private ReservationDAO dao = new JDBCReservationDAO(this.getDataSource());
	private CampgroundDAO campgroundDao = new JDBCCampgroundDAO(this.getDataSource());

	@Test
	public void testSearchReservations() {
		LocalDate fromDate = LocalDate.of(2019, Month.JUNE, 17);
		LocalDate toDate = LocalDate.of(2019, Month.SEPTEMBER, 19);
		LocalDate createDate = LocalDate.of(2019, Month.JUNE, 21);
		Reservation reservation = dao.getReservation(57, 45, "Test Data", fromDate, toDate, createDate);
		Reservation obtainedReservation = null;
		dao.makeReservation(57, "Test Data", fromDate, toDate, createDate);
		String sqlObtainCreatedReservation = "SELECT reservation_id, site_id, name, from_date, to_date, create_date FROM reservation WHERE reservation.name = 'Test Data'";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlObtainCreatedReservation);
		if (results.next()) {
			obtainedReservation = dao.mapRowToReservation(results);
		}
		assertNotNull(obtainedReservation);
		assertEquals(reservation.getCustomerName(), obtainedReservation.getCustomerName());
	}

	@Test
	public void testGetReservationCost() {
		LocalDate fromDate = LocalDate.of(2019, Month.JUNE, 17);
		LocalDate toDate = LocalDate.of(2019, Month.JUNE, 19);
		Campground campground = campgroundDao.getCampground(69, 666, "The Devil's Campground", 01, 12,
				BigDecimal.valueOf(20.00));
		BigDecimal reservationCost = dao.getReservationCost(fromDate, toDate, campground);

		assertNotNull(reservationCost);
		assertEquals(BigDecimal.valueOf(40.00), reservationCost);
	}
}
