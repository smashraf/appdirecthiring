package com.appdirect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2543678914567143256L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String accountIdentifier;

	private String appDirectBaseUrl;
	
	private Integer isActive = 1;
	
	private String status;

	private String uuid;




	@JsonIgnore
	@OneToMany(mappedBy = "account", orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<AppdirectUser> appdirectUser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<SubscriptionDetail> order;

	public UserAccount() {
		super();
	}

	public UserAccount(String uuid, String appDirectBaseUrl, Integer isActive, String status, String testIdentifier,
					   List<AppdirectUser> appdirectUsers, List<SubscriptionDetail> order) {
		super();
		this.accountIdentifier = uuid;
		this.appDirectBaseUrl = appDirectBaseUrl;
		this.isActive = isActive;
		this.status = status;
		this.appdirectUser = appdirectUsers;
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String uuid) {
		this.accountIdentifier = uuid;
	}

	public String getAppDirectBaseUrl() {
		return appDirectBaseUrl;
	}

	public void setAppDirectBaseUrl(String appDirectBaseUrl) {
		this.appDirectBaseUrl = appDirectBaseUrl;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public List<AppdirectUser> getAppdirectUser() {
		return appdirectUser;
	}

	public void setAppdirectUser(List<AppdirectUser> appdirectUsers) {
		this.appdirectUser = appdirectUsers;
	}

	public List<SubscriptionDetail> getOrder() {
		return order;
	}

	public void setOrder(List<SubscriptionDetail> order) {
		this.order = order;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


}
