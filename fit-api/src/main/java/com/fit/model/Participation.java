package com.fit.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fitness_program_has_user")
@IdClass(ParticipationId.class)
public class Participation {
	@Id
	@ManyToOne
	@JoinColumn(name = "fitness_program_id", 
			referencedColumnName = "id")
	private FitnessProgram fitnessProgram;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "username", 
			referencedColumnName = "username")
	private User user;
	
	private Boolean completed;

	public Participation() {
		super();
	}

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

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
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
		Participation other = (Participation) obj;
		return Objects.equals(fitnessProgram, other.fitnessProgram) && Objects.equals(user, other.user);
	}
	
	
	
}
