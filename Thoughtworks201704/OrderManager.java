package com.smc.thoughtworks.billingsystem;

import java.util.Map;

import com.smc.thoughtworks.billingsystem.data.Brand;
import com.smc.thoughtworks.billingsystem.data.Category;
import com.smc.thoughtworks.billingsystem.data.InventoryItem;
import com.smc.thoughtworks.billingsystem.utils.Utils;

/**
 * 
 * @author seshu
 */
public class OrderManager {

	private Map<Integer, InventoryItem> inventory;
	private DiscountManager dc;

	public OrderManager(Map<Integer, InventoryItem> inventory, DiscountManager dc) {
		this.inventory = inventory;
		this.dc = dc;
	}
	

	public float getOrderAmount(String order) {
		float orderAmount = 0;

		String[] items = Utils.trim(Utils.tokenize(order, ","));
		if (Utils.isNullOrEmpty(items)) {
			return 0;
		}

		for (String item : items) {
			orderAmount += getItemAmount(item);
		}

		return orderAmount;
	}
	
	private  float getItemAmount(String item) {
		int id = Utils.toInt(item);
		if (id == 0) {
			return 0;
		}

		InventoryItem inventoryItem = inventory.get(id);
		if (inventoryItem.isInvalid()) {
			return 0;
		}

		Brand brand = inventoryItem.getBrand();
		Category category = inventoryItem.getCategory();

		Integer discount = dc.getNetDiscount(brand, category);
		if (discount == null) { // If not found in netDiscountCatalogue
			return 0;
		}

		float actualPrice = inventoryItem.getPrice();
		float discountedPrice = DiscountUtils.applyDiscount(actualPrice, discount);

		return discountedPrice;
	}

}
