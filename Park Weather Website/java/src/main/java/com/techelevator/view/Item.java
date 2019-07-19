package com.techelevator.view;

import java.math.BigDecimal;

public class Item {

	private String productName;
	private BigDecimal price = new BigDecimal(0);
	private String groupName;
	private int quantity = 5;

	public Item(String productName, BigDecimal price, String groupName) { // Created item class with product name, price, and group name as the constructor
		this.productName = productName;
		this.price = price;
		this.groupName = groupName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
