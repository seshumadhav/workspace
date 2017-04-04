package com.smc.thoughtworks.billingsystem.data;

public class InventoryItem {
	private int id;
	private Brand brand;
	private Category category;
	private float price;

	public InventoryItem(int id, Brand brand, Category category, float price) {
		this.id = id;
		this.brand = brand;
		this.category = category;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public Brand getBrand() {
		return brand;
	}

	public Category getCategory() {
		return category;
	}

	public float getPrice() {
		return price;
	}

	public static InventoryItem newInvalidItem() {
		return new InventoryItem(-1, Brand.INVALID, Category.INVALID, -1);
	}

	public final boolean isInvalid() {
		return getId() == -1 && getBrand().equals(Brand.INVALID) && getCategory().equals(Category.INVALID)
				&& getPrice() == -1;
	}

	@Override
	public String toString() {
		return "[" + getId() + " | " + getBrand() + " | " + getCategory() + " | " + getPrice() + "]";
	}

}