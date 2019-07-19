package com.techelevator.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {

	private static BigDecimal balance = new BigDecimal(0);
	private static Log log = new Log();

	public IO(BigDecimal balance) {
		IO.balance = balance;
	}

	public void feedMoney() throws IOException { // User input feeding the vending machine money
		Scanner feedScanner = new Scanner(System.in);
		System.out.println("Please feed money into the machine in whole dollar amounts: ");
		try {
			BigDecimal userInputMoney = feedScanner.nextBigDecimal();
			if (userInputMoney.compareTo(BigDecimal.valueOf(1.00)) == 0
					|| userInputMoney.compareTo(BigDecimal.valueOf(2.00)) == 0
					|| userInputMoney.compareTo(BigDecimal.valueOf(5.00)) == 0
					|| userInputMoney.compareTo(BigDecimal.valueOf(10.00)) == 0) { // Ensuring the user inserts $1, 2,
																					// 5, 10 only

				balance = balance.add(userInputMoney); // Adding user input's money into a balance
				System.out.println(balance); // Printing balance
				log.creatingLog("FEED MONEY", userInputMoney, getBalance());
			} else {
				System.out.println("Invalid dollar amounts, please try again."); // Catching user inputing incorrect
																					// dollar amount

				feedMoney(); // Returning the user to the feed money screen
			}
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid dollar amount, and try again."); // Catching user input's
																						// non-numeric value selection

			feedMoney();
		}
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		IO.balance = balance;
	}

}
