package com.fit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@JsonFilter("fitnessProgramFilter")
@Entity
public class FitnessProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String difficultyLevel;
	private Double duration;
	private String location;
	private Boolean hidden;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	@ManyToMany
	@JoinTable(name = "image_has_fitness_program", 
			joinColumns = @JoinColumn(name = "fitness_program_id"), 
			inverseJoinColumns = @JoinColumn(name = "image_path"))
	private List<Image> images = new ArrayList<>();
	
	@OneToMany(mappedBy = "fitnessProgram")
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "created_by", 
			referencedColumnName = "username")
	private User createdBy;
	
	@OneToMany(mappedBy = "fitnessProgram")
	private List<Participation> participations = new ArrayList<>();

	public FitnessProgram() {
		super();
	}

	public FitnessProgram(Integer id, String name, String description, Double price, String difficultyLevel, Double duration,
			String location, Boolean hidden) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.difficultyLevel = difficultyLevel;
		this.duration = duration;
		this.location = location;
		this.hidden = hidden;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Image> getImages() {
		return images;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FitnessProgram other = (FitnessProgram) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
