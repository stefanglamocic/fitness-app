package com.fit.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Image {
	@Id
	private String path;
	
	@ManyToMany(mappedBy = "images")
	private List<FitnessProgram> fitnessPrograms = new ArrayList<>();

	public Image() {
		super();
	}

	public Image(String path) {
		super();
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FitnessProgram> getFitnessPrograms() {
		return fitnessPrograms;
	}
	
	
}
