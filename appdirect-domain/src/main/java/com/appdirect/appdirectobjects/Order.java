package com.appdirect.appdirectobjects;



import com.appdirect.appdirectobjects.type.PriceDuration;

import java.util.List;

public class Order {

	private String editionCode;

	private PriceDuration pricingDuration;

	private List<OrderItem> items;

	public String getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public PriceDuration getPricingDuration() {
		return pricingDuration;
	}

	public void setPricingDuration(PriceDuration pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
