package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> searchReservations(int campgroundId, LocalDate startDate, LocalDate endDate) {

		List<Site> sites = new ArrayList<>();
		String sqlGetAllAvailibleSites = "SELECT site.site_id, site.site_number, site.campground_id, max_occupancy, accessible, max_rv_length, utilities " + 
				"FROM site " + 
				"JOIN campground ON campground.campground_id = site.campground_id " + 
				"WHERE site.campground_id = ? AND site.site_id NOT IN " + 
				"(SELECT site.site_id " + 
				"FROM site " + 
				"JOIN reservation ON site.site_id = reservation.site_id " + 
				"WHERE (campground_id = ? AND reservation.from_date BETWEEN ? AND ? ) " + 
				"AND (campground_id = ? AND reservation.to_date BETWEEN ? AND ? )) " + 
				"ORDER BY site.site_id ASC " + 
				"LIMIT 5;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllAvailibleSites, campgroundId, campgroundId, startDate, endDate, campgroundId, startDate, endDate);
		while (results.next()) {
			Site site = mapRowToSite(results);
			sites.add(site);
		}
		return sites;
	}

	@Override
	public Site mapRowToSite(SqlRowSet results) {
		Site site = new Site();
		site.setSiteId(results.getInt("site_id"));
		site.setCampgroundId(results.getInt("campground_id"));
		site.setSiteNumber(results.getInt("site_number"));
		site.setMaxOccupancy(results.getInt("max_occupancy"));
		site.setAcessible(results.getBoolean("accessible"));
		site.setUtilities(results.getBoolean("utilities"));
		site.setMaxRVLength(results.getInt("max_rv_length"));
		return site;
	}
}
