package com.appdirect.appdirectobjects;


import com.appdirect.appdirectobjects.type.*;

public class EventInfo {

	private EventType type;

	private Marketplace marketplace;

	private EventUserInfo creator;

	private PayloadInfo payload;

	private String applicationUuid;

	private EventFlag flag;

	private String returnUrl;

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	public EventUserInfo getCreator() {
		return creator;
	}

	public void setCreator(EventUserInfo creator) {
		this.creator = creator;
	}

	public PayloadInfo getPayload() {
		return payload;
	}

	public void setPayload(PayloadInfo payload) {
		this.payload = payload;
	}

	public String getApplicationUuid() {
		return applicationUuid;
	}

	public void setApplicationUuid(String applicationUuid) {
		this.applicationUuid = applicationUuid;
	}

	public EventFlag getFlag() {
		return flag;
	}

	public void setFlag(EventFlag flag) {
		this.flag = flag;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

}
