package com.smc.thoughtworks.billingsystem.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.smc.thoughtworks.billingsystem.data.Brand;
import com.smc.thoughtworks.billingsystem.data.Category;
import com.smc.thoughtworks.billingsystem.data.InventoryItem;
import com.smc.thoughtworks.utils.Utils;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public class CommandLineReader implements GetsInput {

	Scanner in;

	public CommandLineReader() {
		in = new Scanner(System.in);
	}

	@Override
	public Map<Integer, InventoryItem> getInventory() {
		System.out.println("Enter Inventory: ");

		Map<Integer, InventoryItem> inventory = new HashMap<Integer, InventoryItem>();

		int numInventoryItems = Utils.toInt(in.nextLine());
		for (int i = 0; i < numInventoryItems; i++) {
			String line = in.nextLine();
			InventoryItem inventoryItem = toInventoryItem(line);

			if (!inventoryItem.isInvalid()) {
				inventory.put(inventoryItem.getId(), inventoryItem);
			}
		}

		return inventory;
	}
	
	@Override
	public List<String> getOrders() {
		System.out.println("\nEnter Orders: ");
		
		int numOrders = Utils.toInt(in.nextLine());

		List<String> orders = new ArrayList<>();
		for (int i = 0; i < numOrders; i++) {
			String line = (String) in.nextLine();
			orders.add(line);
		}

		return orders;
	}

	private InventoryItem toInventoryItem(String line) {
		String[] inventoryItemTokens = Utils.tokenize(line, ",");
		return toInventoryItem(inventoryItemTokens);
	}

	public final InventoryItem toInventoryItem(String[] inventoryItemTokens) {
		if (Utils.isNullOrEmpty(inventoryItemTokens) || inventoryItemTokens.length < 4) {
			return InventoryItem.newInvalidItem();
		}

		try {
			int id = new Integer(inventoryItemTokens[0]);
			Brand brand = Brand.fromString(inventoryItemTokens[1]);
			Category category = Category.fromString(inventoryItemTokens[2]);
			float price = new Float(inventoryItemTokens[3]);

			return new InventoryItem(id, brand, category, price);
		} catch (Exception e) {
			return InventoryItem.newInvalidItem();
		}
	}
	
	public void close() {
		in.close();
	}

}
