package com.fit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(length = 1)
	private UserType userType;
	private String name;
	private String surname;
	private String city;
	private String mail;
	private Boolean activated;
	
	public User() {
		super();
	}

	public User(String username, String password, UserType userType, String name, String surname, String city,
			String mail, Boolean activated) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.mail = mail;
		this.activated = activated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	
	
}
