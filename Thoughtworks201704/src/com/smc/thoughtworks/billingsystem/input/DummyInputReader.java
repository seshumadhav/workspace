package com.smc.thoughtworks.billingsystem.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smc.thoughtworks.billingsystem.data.Brand;
import com.smc.thoughtworks.billingsystem.data.Category;
import com.smc.thoughtworks.billingsystem.data.InventoryItem;

/**
 * For Unit testing and dev testing 
 *  
 * @author seshumadhav@gmail.com
 */
public class DummyInputReader implements GetsInput {

	@Override
	public Map<Integer, InventoryItem> getInventory() {
		Map<Integer, InventoryItem> inventory = new HashMap<>();
		
		inventory.put(1, createInventoryItem(1, "Arrow", "SHIRTS", 800));
		inventory.put(2, createInventoryItem(2, "Vero Moda", "DRESSES", 1400));
		inventory.put(3, createInventoryItem(3, "Provogue", "FOOTWEAR", 1800));
		inventory.put(4, createInventoryItem(4, "Wrangler", "JEANS", 2200));
		inventory.put(5, createInventoryItem(5, "UCB", "SHIRTS", 1500));
		
		return inventory;
	}

	@Override
	public List<String> getOrders() {
		List<String> orders = new ArrayList<String>();
		
		orders.add("1, 2, 3, 4");
		orders.add("1, 5");
		
		return orders;
	}
	
	private InventoryItem createInventoryItem(int id, String brand, String category, float price) {
		return new InventoryItem(id, Brand.fromString(brand), Category.fromString(category), price);
	}

}
