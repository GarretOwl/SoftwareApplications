package com.techelevator.npgeek.model.park;

import java.util.List;

public interface ParkDao {
	
	public List<Park> getAllParks ();
	/* returns a list of Parks for each park in database */
	
	public Park getParkByParkCode (String parkCode);
	/* retrieves a Park object analogous to what it represents in the database */
}
