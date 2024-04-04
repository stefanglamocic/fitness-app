package com.fit.model;

import java.time.LocalDateTime;

public class Comment {
	private Integer fitnessProgramId;
	private String publishedBy; //username
	private LocalDateTime published;
	
	private Integer reference; // comment_fitness_program_id
	private String replyingTo; // comment_username
	private LocalDateTime originalPublished; // comment_published
	
	private String content;
}
