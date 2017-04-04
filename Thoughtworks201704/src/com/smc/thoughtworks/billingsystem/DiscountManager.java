package com.smc.thoughtworks.billingsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.smc.thoughtworks.billingsystem.data.Brand;
import com.smc.thoughtworks.billingsystem.data.Category;
import com.smc.thoughtworks.billingsystem.data.InventoryItem;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public class DiscountManager {
	
	private Map<Integer, InventoryItem> inventory;
	private Map<String, Integer> finalDiscounts;

	public DiscountManager(Map<Integer, InventoryItem> inventory) {
		this.inventory = inventory;
		
		finalDiscounts = prepareNetDiscounts();
		dumpDiscountsToConsole();
	}
	
	public Integer getNetDiscount(Brand brand, Category category) {
		return finalDiscounts.get(getKeyInDiscountCatalogue(brand, category));
	}

	private void dumpDiscountsToConsole() {
		System.out.println("\nPrecomputed Discount Catalogue ready!");

		for (Entry<String, Integer> entry : finalDiscounts.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	private Map<String, Integer> prepareNetDiscounts() {
		Map<String, Integer> finalDiscounts = new HashMap<>();

		for (Entry<Integer, InventoryItem> entry : inventory.entrySet()) {
			Brand brand = entry.getValue().getBrand();
			Category category = entry.getValue().getCategory();
			finalDiscounts.put(getKeyInDiscountCatalogue(brand, category), computeNetDiscount(brand, category));
		}

		return finalDiscounts;
	}


	private int computeNetDiscount(Brand brand, Category category) {
		if (category.equals(Category.INVALID)) {
			return 0;
		}

		int discount = 0;
		if (!brand.equals(Brand.INVALID)) {
			discount = category.getDiscount() > brand.getDiscount() ? category.getDiscount() : brand.getDiscount();
		}

		Category parent = category.getParent();
		while (parent != null) {
			discount = parent.getDiscount() > discount ? parent.getDiscount() : discount;
			parent = parent.getParent();
		}

		return discount;
	}
	
	private static final String getKeyInDiscountCatalogue(Brand b, Category c) {
		return b + ":" + c;
	}


}
