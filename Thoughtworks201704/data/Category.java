package com.smc.thoughtworks.billingsystem.data;

enum Category {
	MENS_WEAR(null, 0), SHIRTS(MENS_WEAR, 0), TROUSERS(MENS_WEAR, 0), CASUALS(TROUSERS, 30), JEANS(TROUSERS,
			20), WOMENS_WEAR(null, 50), DRESSES(WOMENS_WEAR, 0), FOOTWEAR(WOMENS_WEAR, 0), INVALID(null, -1);
	;

	private Category parent;
	private Integer discount;

	Category(Category parent, Integer discount) {
		this.parent = parent;
		this.discount = discount;
	}

	public Category getParent() {
		return parent;
	}

	public Integer getDiscount() {
		return discount;
	}

	public static Category fromString(String s) {
		if (s == null || s.isEmpty()) {
			return Category.INVALID;
		}

		for (Category thisCategory : values()) {
			if (s.equalsIgnoreCase(thisCategory.toString())) {
				return thisCategory;
			}
		}

		return Category.INVALID;
	}

}