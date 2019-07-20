package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgrounds() {
		List<Campground> campgrounds = new ArrayList<>();
		String sqlGetAllParks = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Campground campground = mapRowToCampground(results);
			campgrounds.add(campground);
		}
		return campgrounds;
	}

	@Override
	public List<Campground> getCampgroundByParkId(int parkId) {

		List<Campground> campGrounds = new ArrayList<>();
		String sqlGetAllCampgrounds = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCampgrounds, parkId);
		while (results.next()) {
			Campground campGround = mapRowToCampground(results);
			campGrounds.add(campGround);
		}
		return campGrounds;
	}

	@Override
	public Campground mapRowToCampground(SqlRowSet results) {
		Campground campGround = new Campground();
		campGround.setCampgroundId(results.getInt("campground_id"));
		campGround.setParkId(results.getInt("park_id"));
		campGround.setCampgroundName(results.getString("name"));
		campGround.setOpenFrom(results.getInt("open_from_mm"));
		campGround.setOpenTo(results.getInt("open_to_mm"));
		campGround.setDailyFee(results.getBigDecimal("daily_fee"));
		return campGround;
	}

	@Override
	public Campground getCampground(int campground_id, int park_id, String name, int open_from_mm, int open_to_mm,
			BigDecimal daily_fee) {

		Campground campground = new Campground();
		campground.setCampgroundId(campground_id);
		campground.setParkId(park_id);
		campground.setCampgroundName(name);
		campground.setOpenFrom(open_from_mm);
		campground.setOpenTo(open_to_mm);
		campground.setDailyFee(daily_fee);

		return campground;
	}
}