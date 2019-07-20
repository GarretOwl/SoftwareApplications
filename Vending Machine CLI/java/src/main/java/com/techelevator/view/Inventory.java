package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

	private List<String[]> inventoryArrayList = new ArrayList<String[]>();
	private Map<String, Item> inventoryMap = new HashMap<String, Item>();
	private static String selectedItemKey = "";
	private static IO io = new IO(BigDecimal.valueOf(0));
	private static String dispenseMessage = "";
	private static Log log = new Log();

	public void makeInventoryArray() throws FileNotFoundException {

		String vendingMachineFile = "vendingmachine.csv";
		File inventoryFile = new File(vendingMachineFile); // Creating a vending machine file

		Scanner csvScanner = new Scanner(inventoryFile); // Scanning in the vending machine file

		while (csvScanner.hasNextLine()) {
			inventoryArrayList.add(csvScanner.nextLine().split("[|]")); // While there is a next line available to scan in, split the line by "|" into String[] and add it to List

		}
	}

	public void makeInventoryMap() {
		for (int i = 0; i < inventoryArrayList.size(); i++) { // Reiterating the List into a Map
			BigDecimal price = new BigDecimal(inventoryArrayList.get(i)[2]);
			Item inventoryItem = new Item(inventoryArrayList.get(i)[1], price, inventoryArrayList.get(i)[3]); // Creating Item object with product name, price, and group name as the constructor
			inventoryMap.put(inventoryArrayList.get(i)[0], inventoryItem); // Adding key and values to the map
		}
	}

	public void displayVendingItems() {
		for (String item : inventoryMap.keySet()) { // Iterating the Map using the keys to print the inventory list
			System.out.printf("%-5s %-20s $%-5s %-9s Quantity: %-5d %n ", item, inventoryMap.get(item).getProductName(),
					inventoryMap.get(item).getPrice(), inventoryMap.get(item).getGroupName(),
					inventoryMap.get(item).getQuantity());
		}
	}

	public void selectProduct() throws IOException {
		Scanner slotSelectionScanner = new Scanner(System.in); // Selecting the product using the user input
		System.out.print("Please enter the slot ID for your selection: ");
		selectedItemKey = slotSelectionScanner.nextLine();
		if (io.getBalance().compareTo(inventoryMap.get(selectedItemKey).getPrice()) == 0
				|| io.getBalance().compareTo(inventoryMap.get(selectedItemKey).getPrice()) == 1) { // Ensuring the user's balance is more or equal to the price of the product selected

			if (inventoryMap.containsKey(selectedItemKey)) { // Selecting the product using the Map key
				if (inventoryMap.get(selectedItemKey).getQuantity() <= 0) { // When the quantity of the product selected
																			// is 0, unable to purchase, sold out
					System.out.println("SOLD OUT");
				} else {
					log.creatingLog(inventoryMap.get(selectedItemKey).getProductName() + " " + selectedItemKey,
							io.getBalance(), io.getBalance().subtract(inventoryMap.get(selectedItemKey).getPrice()));
					inventoryMap.get(selectedItemKey)
							.setQuantity((inventoryMap.get(selectedItemKey).getQuantity() - 1)); // Setting the quantity of the product 1 less
					io.setBalance(io.getBalance().subtract(inventoryMap.get(selectedItemKey).getPrice())); // Setting the user's balance after purchasing the product
					
				}
				String userInputGroupName = inventoryMap.get(selectedItemKey).getGroupName(); // Return message separated by the group name

				if (userInputGroupName.equals("Chip")) {
					dispenseMessage += "Crunch Crunch, Yum! \n";
				} else if (userInputGroupName.equals("Candy")) {
					dispenseMessage += "Munch Munch, Yum! \n";
				} else if (userInputGroupName.equals("Drink")) {
					dispenseMessage += "Glug Glug, Yum! \n";

				} else {
					dispenseMessage += "Chew Chew, Yum! \n";
				}

			} else {
				System.out.println("Product code does not exist, please try again."); // When user adds slot name that does not exist
			}
		} else {
			System.out.println("Please insert additional money to purchase item."); // When user cannot purchase product due to current balance
		}
	}

	public void finishTransaction() throws IOException { // Return user's change method separated by quarters, dimes, and nickels

		BigDecimal change = io.getBalance(); // Setting the change to the current user's balance

		BigDecimal quarters = change.divideToIntegralValue(BigDecimal.valueOf(0.25)); // Dividing change by 0.25 to configure how many quarters can return
		change = change.subtract(quarters.divide(BigDecimal.valueOf(4))); // Subtracting the change by the number of quarters it will return
		
		BigDecimal dimes = change.divideToIntegralValue(BigDecimal.valueOf(0.10)); // Dividing change by 0.10 to configure how many dimes can return
		change = change.subtract(dimes.multiply(BigDecimal.valueOf(0.10))); // Subtracting the change by the number of dimes it will return

		BigDecimal nickels = change.divideToIntegralValue(BigDecimal.valueOf(0.05)); // Dividing change by 0.05 to configure how many nickels can return
		change = change.subtract(nickels.multiply(BigDecimal.valueOf(0.05))); // Subtracting the change by the number of nickels it will return

		log.creatingLog("GIVE CHANGE", io.getBalance(), BigDecimal.valueOf(0.00));
		io.setBalance(BigDecimal.valueOf(0)); // Setting vending machine balance to 0
		System.out.printf("%-5s quarters: %-5d dimes: %-5d nickels: %-5d %n", "Your change is: ", quarters.intValue(),
				dimes.intValue(), nickels.intValue()); // Printing the user's change through print format and casting Big Decimal to integer
		System.out.println(dispenseMessage); // Printing message(s) of each product purchased
	}

	public String getDispenseMessage() {
		return dispenseMessage;
	}

	public String getSelectedItemKey() {
		return selectedItemKey;
	}

	public List<String[]> getInventoryArrayList() {
		return inventoryArrayList;
	}

	public Map<String, Item> getInventoryMap() {
		return inventoryMap;
	}

	public void setInventoryMap(Map<String, Item> inventoryMap) {
		this.inventoryMap = inventoryMap;
	}

	public static Log getLog() {
		return log;
	}

	public static IO getIo() {
		return io;
	}

}
