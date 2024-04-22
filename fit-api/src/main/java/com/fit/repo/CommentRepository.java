package com.fit.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.Comment;
import com.fit.model.CommentId;
import com.fit.model.FitnessProgram;
import com.fit.model.User;

public interface CommentRepository extends JpaRepository<Comment, CommentId>{
	public Comment findByFitnessProgramAndPublishedByAndPublished(
			FitnessProgram fitnessProgram, User publishedBy, LocalDateTime published);
}
