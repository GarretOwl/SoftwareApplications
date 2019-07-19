package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface SiteDAO {

	public List<Site> searchReservations(int campgroundId, LocalDate startDate, LocalDate endDate);
	/* return a list of availible sites for reservations */

	public Site mapRowToSite(SqlRowSet results);
	/* takes rows from queries and maps them to Site objects */
}
