package com.appdirect.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subs_order_detail")
public class SubscriptionDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 125610036507537001L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String editionCode;

	private String pricingDuration;

	@OneToMany(mappedBy = "order", orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<OrderDetails> orderDetails;

	@ManyToOne
	@JoinColumn(name = "account_id_fk")
	private UserAccount account;

	public SubscriptionDetail() {
		super();
	}

	
	public SubscriptionDetail(String editionCode, String pricingDuration, List<OrderDetails> items, UserAccount account) {
		super();
		this.editionCode = editionCode;
		this.pricingDuration = pricingDuration;
		this.orderDetails = items;
		this.account = account;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEditionCode() {
		return editionCode;
	}

	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public String getPricingDuration() {
		return pricingDuration;
	}

	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(List<OrderDetails> orderSkews) {
		this.orderDetails = orderSkews;
	}


	public UserAccount getUserAccount() {
		return account;
	}

	public void setUserAccount(UserAccount account) {
		this.account = account;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((editionCode == null) ? 0 : editionCode.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + ((pricingDuration == null) ? 0 : pricingDuration.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionDetail other = (SubscriptionDetail) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (editionCode == null) {
			if (other.editionCode != null)
				return false;
		} else if (!editionCode.equals(other.editionCode))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (pricingDuration == null) {
			if (other.pricingDuration != null)
				return false;
		} else if (!pricingDuration.equals(other.pricingDuration))
			return false;
		return true;
	}

}
