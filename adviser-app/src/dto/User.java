package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	private String username, password, userType, name, surname, mail;
	private Boolean activated;
	private List<Message> inbox;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public User() {
		this.inbox = new ArrayList<Message>();
	}

	public User(String username, String password, String userType, String name, String surname,
			String mail, Boolean activated, List<Message> inbox) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.activated = activated;
		this.inbox = inbox;
	}
	
	public User(String username, String password, String userType, String name, String surname, String mail, 
				Boolean activated) {
		this();
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.name = name;
		this.surname = surname;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
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

	public List<Message> getInbox() {
		return inbox;
	}

	public void setInbox(List<Message> inbox) {
		this.inbox = inbox;
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
