package com.techelevator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Log {

	private SimpleDateFormat dateFormat;
	private Date date = new Date();
	private static IO io = new IO(BigDecimal.valueOf(0));
	private File createLogFile = new File("Log.txt");
	private Scanner logScanner = new Scanner(System.in);

	public void creatingLog(String message, BigDecimal balance1, BigDecimal balance2) throws IOException { //this is the method that prints to the log as pertinent methods are called
		dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
		date = new Date(System.currentTimeMillis());
		createLogFile.createNewFile();
		PrintWriter writer = new PrintWriter(new FileWriter(createLogFile, true)); //condition that prevents log from overwriting itself
		
		writer.println(dateFormat.format(date) + " " + message + " $" + balance1 + " $" + balance2);
		writer.flush(); //prevents log from reiterating
	}

}
