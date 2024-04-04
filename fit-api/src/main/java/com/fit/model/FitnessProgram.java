package com.fit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class FitnessProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;
	private String difficultyLevel;
	private Double duration;
	private String location;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@OneToOne
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
}
