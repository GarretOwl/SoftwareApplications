package com.techelevator.campground.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface ParkDAO {

	public List<Park> getAllParks();
	/* return list of parks and an option to break out of program */
	
	public Park mapRowToPark(SqlRowSet results); 
	
	public Park getParkById(int parkId);
	/* fetches park by Id */
}


