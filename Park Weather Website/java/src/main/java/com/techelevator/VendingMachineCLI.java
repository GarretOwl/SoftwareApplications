package com.techelevator;

import java.io.IOException;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws IOException {
		while (true) {
			menu.mainMenu();
		}
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		menu.getInventory().makeInventoryArray();
		menu.getInventory().makeInventoryMap();

		cli.run();
	}
}
