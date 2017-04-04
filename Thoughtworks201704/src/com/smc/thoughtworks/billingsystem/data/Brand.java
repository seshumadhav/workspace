package com.smc.thoughtworks.billingsystem.data;

import com.smc.thoughtworks.utils.Utils;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public enum Brand {
	WRANGLER("Wrangler", 10), 
	ARROW("Arrow", 20), 
	VERO_MODA("Vero Moda", 60), 
	UCB("UCB", 0), 
	ADIDAS("Adidas", 5), 
	PROVOGUE("Provogue", 20),
	INVALID("Invalid", -1)
	;

	private int discount;
	private String name;

	Brand(String name, int discount) {
		this.name = name;
		this.discount = discount;
	}

	public int getDiscount() {
		return this.discount;
	}

	public String getName() {
		return this.name;
	}

	public static Brand fromString(String s) {
		if (Utils.isNullOrEmpty(s)) {
			return Brand.INVALID;
		}

		for (Brand thisBrand : values()) {
			if (s.equalsIgnoreCase(thisBrand.getName())) {
				return thisBrand;
			}
		}

		return Brand.INVALID;
	}

}