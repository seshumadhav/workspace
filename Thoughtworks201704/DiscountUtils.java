package com.smc.thoughtworks.billingsystem;

class DiscountUtils {
	
	public static final float applyDiscount(float actual, int discount) {
		if (actual == -1) {
			return -1;
		}
		
		return actual * (100 - discount) / 100;
	}
	
}