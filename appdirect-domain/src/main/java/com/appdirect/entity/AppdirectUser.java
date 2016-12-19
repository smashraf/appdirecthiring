package com.appdirect.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "appdirect_user")
public class AppdirectUser implements Serializable{

	private static final long serialVersionUID = -223233452969421227L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;

	private String email;

	private String firstName;

	private String language;

	private String lastName;

	private String openId;

	private String uuid;

	private boolean isAdmin;


	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "account_id_fk")
	private UserAccount account;



	public AppdirectUser() {
		super();
	}

	public AppdirectUser(String email, String firstName, String language, String lastName, String openId, String uuid,
				boolean isAdmin) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.language = language;
		this.lastName = lastName;
		this.openId = openId;
		this.uuid = uuid;
		this.isAdmin = isAdmin;
		this.account = account;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}
	
}
