package com.appdirect.appdirectobjects;

public class PayloadInfo {

	private Organization company;

	private Order order;

	private Account account;

	private EventUserInfo user;

	private EventNoticeInfo notice;

	public Organization getCompany() {
		return company;
	}

	public void setCompany(Organization company) {
		this.company = company;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public EventUserInfo getUser() {
		return user;
	}

	public void setUser(EventUserInfo user) {
		this.user = user;
	}

	public EventNoticeInfo getNotice() {
		return notice;
	}

	public void setNotice(EventNoticeInfo notice) {
		this.notice = notice;
	}

}
