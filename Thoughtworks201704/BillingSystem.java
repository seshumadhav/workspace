package com.smc.thoughtworks.billingsystem;

import java.util.List;
import java.util.Map;

import com.smc.thoughtworks.billingsystem.data.InventoryItem;
import com.smc.thoughtworks.billingsystem.input.InputReader;

/**
 * @author seshumadhav@gmail.com
 *
 */
public class BillingSystem {

	InputReader inputReader;

	public BillingSystem(InputReader inputReader) {
		this.inputReader = inputReader;
	}

	public void go() {
		Map<Integer, InventoryItem> inventory = inputReader.readInventoryFromCommandLine();

		// Typically inventory gets updated once a day and there can be numerous
		// orders in 1 day. Precompute so that we don't have to do the math for every order.
		DiscountManager discountManager = new DiscountManager(inventory);

		List<String> orders = inputReader.readOrdersFromCommandLine();
		OrderManager orderManager = new OrderManager(inventory, discountManager);
		System.out.println("\nOrder amounts");
		for (String order : orders) {
			Float orderAmount = orderManager.getOrderAmount(order);
			System.out.println(orderAmount);
		}

	}

	public static void main(String[] args) {
		InputReader inputReader = new InputReader();
		BillingSystem billingSystem = new BillingSystem(inputReader);
		
		billingSystem.go();
	}

}