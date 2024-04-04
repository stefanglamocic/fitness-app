package com.fit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Attribute {
	@Id
	private String name;
	
	public Attribute() {}
	
	public Attribute(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
