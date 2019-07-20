package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Reservation retrieveReservation(String reservationName) {
		Reservation reservation = new Reservation();
		String sqlRetrieveReservation = "SELECT reservation_id, site_id, name, from_date, to_date, create_date FROM reservation WHERE name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlRetrieveReservation, reservationName);
		while (results.next()) {
			reservation = mapRowToReservation(results);
		}
		return reservation;
	}

	@Override
	public void makeReservation(int siteId, String name, LocalDate fromDate, LocalDate toDate, LocalDate createDate) {
		String sqlInsertReservation = "INSERT INTO reservation(site_id, name, from_date, to_date, create_date) "
				+ "VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertReservation, siteId, name, fromDate, toDate, createDate);
	}

	@Override
	public Reservation mapRowToReservation(SqlRowSet results) {
		Reservation reservation = new Reservation();
		reservation.setReservationId(results.getInt("reservation_id"));
		reservation.setSiteId(results.getInt("site_id"));
		reservation.setCustomerName(results.getString("name"));
		reservation.setFromDate(results.getDate("from_date").toLocalDate());
		reservation.setToDate(results.getDate("to_date").toLocalDate());
		reservation.setCreateDate(results.getDate("create_date").toLocalDate());

		return reservation;
	}

	@Override
	public BigDecimal getReservationCost(LocalDate fromDate, LocalDate toDate, Campground reservationCampground) {
		BigDecimal dailyParkFee = reservationCampground.getDailyFee();
		long stayDuration = ChronoUnit.DAYS.between(fromDate, toDate);
		BigDecimal reservationCost = dailyParkFee.multiply(BigDecimal.valueOf(stayDuration));

		return reservationCost;
	}

	@Override
	public Reservation makeReservation(int reservation_id, int site_id, String name, LocalDate from_date,
			LocalDate to_date, LocalDate create_date) {

		Reservation reservation = new Reservation();
		reservation.setReservationId(reservation_id);
		reservation.setSiteId(site_id);
		reservation.setCustomerName(name);
		reservation.setFromDate(from_date);
		reservation.setToDate(to_date);
		reservation.setCreateDate(create_date);

		return reservation;
	}
}