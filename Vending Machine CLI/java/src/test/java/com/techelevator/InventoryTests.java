package com.techelevator;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.Inventory;
import com.techelevator.view.Item;
import com.techelevator.view.Menu;

public class InventoryTests {

	Inventory inventory = new Inventory();

	@Test
	public void inventoryArrayTest() throws FileNotFoundException {
		inventory.makeInventoryArray();
		String[] test = new String[] { "A1", "Potato Crisps", "3.05", "Chip" };	
		Assert.assertEquals(test[1], inventory.getInventoryArrayList().get(0)[1]); //String[] test is the first String[] in Inventory List<String[]>
		
		Assert.assertEquals(16, inventory.getInventoryArrayList().size()); //Size of the Inventory List<String[]> is 16
	}

	@Test
	public void inventoryMapTest() throws FileNotFoundException {
		inventory.makeInventoryArray();
		inventory.makeInventoryMap();
		
		Assert.assertEquals(inventory.getInventoryMap().get("A1").getProductName(), "Potato Crisps"); // Product name of slot A1 is Potato Crisps
		
		Assert.assertEquals(inventory.getInventoryMap().get("A3").getPrice(), BigDecimal.valueOf(2.75));	//Price of slot A3 is 2.75
		
		Assert.assertEquals(inventory.getInventoryMap().get("A3").getGroupName(), "Chip");	//Product name of slot A3 is Chip
		
		Assert.assertTrue(inventory.getInventoryMap().containsKey("A1"));	//Inventory List<String[]> contains slot name A1
		
		Assert.assertTrue(inventory.getInventoryMap().containsKey("A2"));	//Inventory List<String[]> contains slot name A2
		
		Assert.assertEquals(BigDecimal.valueOf(1.25), inventory.getInventoryMap().get("C1").getPrice());	//Price of slot C1 is 1.25

	}
		
}
