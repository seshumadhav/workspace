package com.smc.thoughtworks.billingsystem.input;

import java.util.List;
import java.util.Map;

import com.smc.thoughtworks.billingsystem.data.InventoryItem;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public interface GetsInput {

	Map<Integer, InventoryItem> getInventory();
	
	List<String> getOrders();
	
}
