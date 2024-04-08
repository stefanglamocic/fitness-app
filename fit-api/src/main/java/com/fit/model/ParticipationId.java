package com.fit.model;

import java.io.Serializable;
import java.util.Objects;

public class ParticipationId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer fitnessProgramId;
	private String username;
	
	public ParticipationId() {
		super();
	}

	public Integer getFitnessProgramId() {
		return fitnessProgramId;
	}

	public void setFitnessProgramId(Integer fitnessProgramId) {
		this.fitnessProgramId = fitnessProgramId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fitnessProgramId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipationId other = (ParticipationId) obj;
		return Objects.equals(fitnessProgramId, other.fitnessProgramId) && Objects.equals(username, other.username);
	}
	
	
	
}
