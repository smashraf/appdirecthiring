package com.appdirect.appdirectobjects;


import com.appdirect.appdirectobjects.type.PriceUnit;

public class OrderItem {

	private Integer quantity;

	private PriceUnit unit;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public PriceUnit getUnit() {
		return unit;
	}

	public void setUnit(PriceUnit unit) {
		this.unit = unit;
	}

}
