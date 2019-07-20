package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface CampgroundDAO {

	public List<Campground> getCampgroundByParkId(int parkId);
	/* return a list of campgrounds by the park Id */

	public Campground mapRowToCampground(SqlRowSet results);
	/* maps a row from the campground table to a Campground object */
	
	public Campground getCampground(int campground_id, int park_id, String name, int open_from_mm, int open_to_mm,
			BigDecimal daily_fee);
	/* creates a campground for testing purposes */
	
	public List<Campground> getAllCampgrounds();
}
