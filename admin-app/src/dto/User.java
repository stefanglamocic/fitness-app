package dto;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String username, password, userType, name, surname, city, mail;
	private Boolean activated;
	
	public User() {}
	
	public User(String username) {
		this.username = username;
	}
	
	public User(String username, String password, String userType, String name, String surname, String city,
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userType=" + userType + ", name=" + name
				+ ", surname=" + surname + ", city=" + city + ", mail=" + mail + ", activated=" + activated + "]";
	}
}
