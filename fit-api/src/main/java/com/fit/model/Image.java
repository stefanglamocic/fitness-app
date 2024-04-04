package com.fit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Image {
	@Id
	private String path;

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
	
	
}
