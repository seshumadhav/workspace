package com.smc.thoughtworks.billingsystem.utlls;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public class BillingSystemUtils {
	
	public static final float applyDiscount(float actual, int discount) {
		if (actual == -1) {
			return -1;
		}
		
		return actual * (100 - discount) / 100;
	}
	
}