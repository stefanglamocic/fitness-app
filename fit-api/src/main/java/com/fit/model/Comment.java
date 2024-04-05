package com.fit.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@JsonIgnoreProperties(value = {"fitnessProgram", "publishedBy"})
@Entity
@IdClass(CommentId.class)
public class Comment {
	@ManyToOne
	@JoinColumn(name = "fitness_program_id", 
			referencedColumnName = "id")
	private FitnessProgram fitnessProgram;
	
	@ManyToOne
	@JoinColumn(name = "username", 
			referencedColumnName = "username")
	private User publishedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private LocalDateTime published;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "comment_fitness_program_id", 
				referencedColumnName = "fitness_program_id"),
		@JoinColumn(name = "comment_username", 
				referencedColumnName = "username"),
		@JoinColumn(name = "comment_published", 
				referencedColumnName = "published")
	})
	private Comment parentComment;
	
	@OneToMany(mappedBy = "parentComment")
	private List<Comment> childComments = new ArrayList<>();
	
	private String content;

	public Comment() {
		super();
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

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getChildComments() {
		return childComments;
	}
	
	
	
}
