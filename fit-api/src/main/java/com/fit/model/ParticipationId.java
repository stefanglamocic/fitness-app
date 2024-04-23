package com.fit.model;

import java.io.Serializable;
import java.util.Objects;

public class ParticipationId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FitnessProgram fitnessProgram;
	private User user;
	
	public ParticipationId() {}

	public FitnessProgram getFitnessProgram() {
		return fitnessProgram;
	}

	public void setFitnessProgram(FitnessProgram fitnessProgram) {
		this.fitnessProgram = fitnessProgram;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fitnessProgram, user);
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
		return Objects.equals(fitnessProgram, other.fitnessProgram) && Objects.equals(user, other.user);
	}

}
