package com.fit.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Attribute {
	@Id
	private String name;
	@JsonIgnore
	@ManyToMany(mappedBy = "attributes")
	private List<Category> categories = new ArrayList<>();
	
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
