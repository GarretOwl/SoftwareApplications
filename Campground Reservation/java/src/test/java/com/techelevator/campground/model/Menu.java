package com.techelevator.campground.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

public class Menu {

	public DataSource dataSource;
	private PrintWriter out;
	private Scanner in;
	private String mainMenuUserInput = "";
	private String subMenuUserInput = "";
	private String userInputCampground = "";
	private String userInputSite = "";
	private String userInputReservation = "";
	private String userParkName = "";
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private BigDecimal stayCost;
	private ReservationDAO reservationDAO;
	private int userInputCampgroundInteger;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public void mainMenu(List<Park> parkList) {
		boolean mainMenuSafeguardBoolean = false;
		System.out.println("Campground Reservation System");
		System.out.println("View Parks for Further Details");
		System.out.println("Select a park for further Details\n");

		int count = 0;
		for (int i = 0; i < parkList.size(); i++) {
			count += 1;
			System.out.println(count + ". " + parkList.get(i).getParkName());
		}
		System.out.println("\nQ. Quit");
		while (!mainMenuSafeguardBoolean) {
			mainMenuUserInput = in.nextLine();
			if (mainMenuUserInput.equals("1") || mainMenuUserInput.equals("2") || mainMenuUserInput.equals("3")
					|| mainMenuUserInput.equalsIgnoreCase("Q")) {
				mainMenuSafeguardBoolean = true;
				if (mainMenuUserInput.equalsIgnoreCase("Q")) {
					System.out.println("Thank you for using Campground reservation systems!");
					System.exit(0);
				}
			} else {
				System.out.println("\nPlease enter a valid option and try again: \n");
			}
		}
	}

	public void SubMenu(Park park, String userInputPark) {

		setUserParkName(park.getParkName());
		System.out.println("Park Infomation Screen\n");
		System.out.println(park.getParkName() + " National Park" + "\n");
		System.out.println("Location: " + park.getParkLocation());
		System.out.println("Established: " + park.getEstablishedDate());
		System.out.println("Area: " + park.getParkArea() + " sql km");
		System.out.println("Anuual Visitors: " + park.getAnnualVisitors());
		System.out.println("\n" + park.getParkDescription() + "\n");
		System.out.println("Select a command \n");
		System.out.println("1. View Campgrounds");
		System.out.println("2. Search Reservation");
		System.out.println("3. Return to Previous Screen\n");

		boolean subMenuSafeguardBoolean = false;
		while (!subMenuSafeguardBoolean) {
			subMenuUserInput = in.nextLine();
			if (subMenuUserInput.equals("1") || subMenuUserInput.equals("2") || subMenuUserInput.equals("3")) {
				subMenuSafeguardBoolean = true;
			} else {
				System.out.println("\nPlease enter a valid option and try again: \n");
			}
		}
	}

	public void campgroundMenu(List<Campground> campgrounds) {

		if (subMenuUserInput.equals("1")) {
			System.out.println(userParkName + " National Park Campgrounds\n");
			System.out.printf("%-15s %-33s %-15s %-15s %15s %n", " ", "Name", "Month Open", "Month Closed",
					"Daily Fee");
			for (int i = 0; i < campgrounds.size(); i++) {

				System.out.printf("%-15s %-33s %-15s %-21s $%-15.2f %n", campgrounds.get(i).getCampgroundId(),
						campgrounds.get(i).getCampgroundName(), campgrounds.get(i).getOpenFrom(),
						campgrounds.get(i).getOpenTo(), campgrounds.get(i).getDailyFee());

			}
			System.out.println("\n1. Return to Previous Screen");
			System.out.println("2. Search for Availible Reservation");

			boolean campgroundSafeguardBoolean = false;
			while (!campgroundSafeguardBoolean) {
				subMenuUserInput = in.nextLine();
				if (subMenuUserInput.equals("1") || subMenuUserInput.equals("2")) {
					campgroundSafeguardBoolean = true;
				} else {
					System.out.println("\nPlease enter a valid option and try again: \n");
				}
			}
		} else {
			return;
		}
	}

	public void reservationMenu(List<Campground> campgrounds, List<Campground> allCampgrounds) {

		if (subMenuUserInput.equals("2")) {
			System.out.println("Search for Campground Reservation");
			System.out.println(userParkName + " National Park Campgrounds\n");
			System.out.printf("%-15s %-33s %-15s %-15s %15s %n", " ", "Name", "Month Open", "Month Closed",
					"Daily Fee");

			for (int i = 0; i < campgrounds.size(); i++) {

				System.out.printf("%-15s %-33s %-15s %-21s $%-15.2f %n", campgrounds.get(i).getCampgroundId(),
						campgrounds.get(i).getCampgroundName(), campgrounds.get(i).getOpenFrom(),
						campgrounds.get(i).getOpenTo(), campgrounds.get(i).getDailyFee());
			}

			boolean reservationSafeguardBoolean = false;
			while (!reservationSafeguardBoolean) {
				try {
					System.out.println("Which campground [enter 0 to cancel]?");
					userInputCampground = in.next();
					if (userInputCampground.equals("0")) {
						return;
					}
					setUserInputCampgroundInteger(Integer.parseInt(userInputCampground) - 1);
					Campground userChosenCampground = allCampgrounds.get(userInputCampgroundInteger);

						System.out.println("What is the arrival date? YYYY-MM-DD");
					arrivalDate = LocalDate.parse(in.next());
					LocalDate currentDate = LocalDate.now();
					if (arrivalDate.compareTo(currentDate) < 0) {
						System.out.println("Reservation cannot be booked on past date. Please enter a valid date.");
					} else {
						System.out.println("What is the departure date? YYYY-MM-DD");
						departureDate = LocalDate.parse(in.next());
						if (departureDate.compareTo(arrivalDate) < 0) {
							System.out.println(
									"Departure date cannot be booked before your arrival date. Please enter a valid date.");
						} else {
							stayCost = reservationDAO.getReservationCost(arrivalDate, departureDate,
									userChosenCampground);
							reservationSafeguardBoolean = true;
						}
					}
				} catch (NumberFormatException e) {
					System.out.println("Please enter an appropriate numeric value");
				} catch (DateTimeParseException e) {
					System.out.println("Please enter the date in the correct YYYY-MM-DD format.");
				}
				userInputSite = "1";
				setUserInputCampgroundInteger(getUserInputCampgroundInteger() + 1);
			}
		}
	}

	public void sitereservationMenu(List<Site> sites, BigDecimal cost, List<Campground> campgrounds,
			List<Campground> allCampgrounds) {
		if (userInputSite.equals("1")) {
			while (sites.size() == 0) {
				System.out.println(
						"There are no reservations availible for this date range. Would you like to enter another date range? [Please enter 'Yes' or 'No']");
				
				String userReservationInput = in.next();
				if (userReservationInput.equalsIgnoreCase("Yes")) {
					reservationMenu(campgrounds, allCampgrounds);
					sitereservationMenu(sites, cost, campgrounds, allCampgrounds);
				} else if (userReservationInput.equalsIgnoreCase("No")) {
					return;
				} else {
					System.out.println("Please enter either 'Yes' or 'No'");
				}
			}
			System.out.println("Results Matching Your Search Criteria");
			System.out.printf("%-15s %-15s %-33s %-15s %-15s %-15s %-15s %n", "Campsite Number", "Site Id.", "Max Occup.", "Accessible?",
					"Max RV Length", "Utility", "Cost");
			int count = 0;
			for (int i = 0; i < sites.size(); i++) {
				count++;
				System.out.printf("%-15s %-15s %-33s %-15s %-21s %-15s $%-15.2f %n", count, sites.get(i).getSiteId(),
						sites.get(i).getMaxOccupancy(), sites.get(i).isAcessible(), sites.get(i).getMaxRVLength(),
						sites.get(i).isUtilities(), stayCost);
			}
			System.out.print("Which site should be reserved [enter 0 to cancel]?");
			int userInputReservationSiteNumber = Integer.parseInt(in.next());
			System.out.print("What name should the reservation be under?");
			String reservationName = in.next();
			Site site = sites.get(userInputReservationSiteNumber - 1);
			LocalDate currentDate = LocalDate.now();
			reservationDAO.makeReservation(site.getSiteNumber(), reservationName, arrivalDate, departureDate,
					currentDate);
			Reservation reservation = reservationDAO.retrieveReservation(reservationName);
			System.out.println("The reservation has been made and the confirmation id is: "
					+ reservation.getReservationId() + "\n");
			System.out.println("");
		}
	}

	public int getUserInputCampgroundInteger() {
		return userInputCampgroundInteger;
	}

	public void setUserInputCampgroundInteger(int userInputCampgroundInteger) {
		this.userInputCampgroundInteger = userInputCampgroundInteger;
	}

	public BigDecimal getStayCost() {
		return stayCost;
	}

	public void setStayCost(BigDecimal stayCost) {
		this.stayCost = stayCost;
	}

	public ReservationDAO getReservationDAO() {
		return reservationDAO;
	}

	public void setReservationDAO(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public String getMainMenuUserInput() {
		return mainMenuUserInput;
	}

	public void setMainMenuUserInput(String userInputPark) {
		this.mainMenuUserInput = userInputPark;
	}

	public String getUserInputCampground() {
		return userInputCampground;
	}

	public void setUserInputCampground(String userInputCampground) {
		this.userInputCampground = userInputCampground;
	}

	public String getUserInputSite() {
		return userInputSite;
	}

	public void setUserInputSite(String userInputSite) {
		this.userInputSite = userInputSite;
	}

	public String getUserInputReservation() {
		return userInputReservation;
	}

	public void setUserInputReservation(String userInputReservation) {
		this.userInputReservation = userInputReservation;
	}

	public String getUserParkName() {
		return userParkName;
	}

	public void setUserParkName(String userParkName) {
		this.userParkName = userParkName;
	}
}
