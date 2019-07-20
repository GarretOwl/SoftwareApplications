package com.techelevator.campground.model;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.JDBCCampgroundDAO;
import com.techelevator.campground.model.JDBCParkDAO;
import com.techelevator.campground.model.JDBCReservationDAO;
import com.techelevator.campground.model.JDBCSiteDAO;
import com.techelevator.campground.model.Menu;
import com.techelevator.campground.model.ParkDAO;
import com.techelevator.campground.model.SiteDAO;

public class CampgroundCLI {

	private Menu menu;
	private CampgroundDAO campgroundDAO;
	private ParkDAO parkDAO;
	private SiteDAO siteDAO;

	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}

	public CampgroundCLI() {
		this.menu = new Menu(System.in, System.out);

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		siteDAO = new JDBCSiteDAO(dataSource);
		parkDAO = new JDBCParkDAO(dataSource);
		menu.setReservationDAO(new JDBCReservationDAO(dataSource));

	}

	public void run() {
		boolean cliBoolean = true;
		while (cliBoolean) {
			menu.mainMenu(parkDAO.getAllParks());
			menu.SubMenu(parkDAO.getParkById(Integer.parseInt(menu.getMainMenuUserInput())),
					menu.getMainMenuUserInput());
			menu.campgroundMenu(campgroundDAO.getCampgroundByParkId(Integer.parseInt(menu.getMainMenuUserInput())));
			menu.reservationMenu(campgroundDAO.getCampgroundByParkId(Integer.parseInt(menu.getMainMenuUserInput())),
					campgroundDAO.getAllCampgrounds());
			menu.sitereservationMenu(siteDAO.searchReservations(menu.getUserInputCampgroundInteger(), menu.getArrivalDate(),
					menu.getDepartureDate()),
					menu.getStayCost(),
					campgroundDAO.getCampgroundByParkId(Integer.parseInt(menu.getMainMenuUserInput())),
					campgroundDAO.getAllCampgrounds());

		}
	}
}