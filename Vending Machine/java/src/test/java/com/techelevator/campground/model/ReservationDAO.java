package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface ReservationDAO {

	public void makeReservation(int siteId, String name, LocalDate fromDate, LocalDate toDate, LocalDate createDate);
	/* inserts a new reservation into the reservation table */

	public BigDecimal getReservationCost(LocalDate fromDate, LocalDate toDate, Campground reservationCampground);
	/* returns the cost of a stay at a campground's site */

	public Reservation mapRowToReservation(SqlRowSet results);
	/* maps a row from the reservation table to a Reservation object */

	public Reservation getReservation(int reservation_id, int site_id, String name, LocalDate from_date,
			LocalDate to_date, LocalDate create_date);

	public Reservation retrieveReservation(String reservationName);
}
