package com.techelevator.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	private static IO io = new IO(BigDecimal.valueOf(0));
	private static Inventory inventory = new Inventory();

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public void mainMenu() throws IOException {
		try {
			System.out.println("Please enter a selection:");
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");

			Scanner menuScanner = new Scanner(System.in);
			int userInputMainMenu = menuScanner.nextInt();

			// Menu branches here
			if (userInputMainMenu == 1) {
				inventory.displayVendingItems();
				mainMenu();
			} else if (userInputMainMenu == 2) {
				subMenu();
				// Purchase menu branches here
			} else {
				System.out.println("Please enter either 1 or 2"); // Catching incorrect user input selection for main
																	// menu
				mainMenu(); // Returning user to the main menu
			}
		} catch (InputMismatchException e) {
			System.out.print("Please enter a valid number option."); // Catching user input's non-numeric value
																		// selection
			mainMenu();
		} catch (NullPointerException e) {
			System.out.print("Please enter a valid number option."); // Catching user input's non-numeric value
																		// selection
			mainMenu();
		}
	}

	public void subMenu() throws IOException {
		try {
			System.out.println("(1) Feed Money"); // Sub-menu with user input's selection
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");
			System.out.println("Current Money Provided: $" + io.getBalance());

			Scanner subMenuScanner = new Scanner(System.in);
			int userInputSubMenu = subMenuScanner.nextInt();
			
			if (userInputSubMenu == 1) { // User input selecting to feed money into vending machine
				io.feedMoney(); // Start feed money method
				subMenu(); // Return user to sub-menu after feeding money
			
			} else if (userInputSubMenu == 2) { // User input selecting to select the product
				inventory.selectProduct(); // Start select product method
				subMenu(); // Return user to sub-menu after selecting a product
			
			} else if (userInputSubMenu == 3) { // User input selecting to end the transaction
				inventory.finishTransaction(); // Start finish transaction method
				mainMenu(); // Return user to the main menu after completing transaction
		
			} else {
				System.out.println("Please enter either 1, 2 or 3"); // Catching user input's incorrect selection to the
																		// sub-menu
				subMenu(); // Return user to sub-menu
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid number option."); // Catching user input's non-numeric selection
			subMenu(); // Return user to sub-menu
	
		} catch (NullPointerException e) {
			System.out.println("Please enter a valid slot number."); // Catching user input's non-numeric value
																		// selection
			subMenu();
		}
	}

	public Inventory getInventory() {
		return inventory;
	}
}