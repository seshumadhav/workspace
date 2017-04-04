package com.smc.thoughtworks.billingsystem;

import java.util.Map;

import com.smc.thoughtworks.billingsystem.data.Brand;
import com.smc.thoughtworks.billingsystem.data.Category;
import com.smc.thoughtworks.billingsystem.data.InventoryItem;
import com.smc.thoughtworks.billingsystem.utlls.BillingSystemUtils;
import com.smc.thoughtworks.utils.Utils;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public class OrderManager {

	private Map<Integer, InventoryItem> inventory;
	private DiscountManager discountManager;

	public OrderManager(Map<Integer, InventoryItem> inventory, DiscountManager discountManager) {
		this.inventory = inventory;
		this.discountManager = discountManager;
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
	
	private float getItemAmount(String item) {
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

		Integer discount = discountManager.getNetDiscount(brand, category);
		if (discount == null) { // If not found in netDiscountCatalogue
			return 0;
		}

		float actualPrice = inventoryItem.getPrice();
		float discountedPrice = BillingSystemUtils.applyDiscount(actualPrice, discount);

		return discountedPrice;
	}

}
