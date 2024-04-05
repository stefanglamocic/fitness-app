package com.fit.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommentId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FitnessProgram fitnessProgram;
	private User publishedBy;
	private LocalDateTime published;
	
	public CommentId() {
		super();
	}

	public CommentId(FitnessProgram fitnessProgram, User publishedBy, LocalDateTime published) {
		super();
		this.fitnessProgram = fitnessProgram;
		this.publishedBy = publishedBy;
		this.published = published;
	}

	public FitnessProgram getFitnessProgram() {
		return fitnessProgram;
	}

	public void setFitnessProgram(FitnessProgram fitnessProgram) {
		this.fitnessProgram = fitnessProgram;
	}

	public User getPublishedBy() {
		return publishedBy;
	}

	public void setPublishedBy(User publishedBy) {
		this.publishedBy = publishedBy;
	}

	public LocalDateTime getPublished() {
		return published;
	}

	public void setPublished(LocalDateTime published) {
		this.published = published;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fitnessProgram, published, publishedBy);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentId other = (CommentId) obj;
		return Objects.equals(fitnessProgram, other.fitnessProgram) && Objects.equals(published, other.published)
				&& Objects.equals(publishedBy, other.publishedBy);
	}
	
	

}
