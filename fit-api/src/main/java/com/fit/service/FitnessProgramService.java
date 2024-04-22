package com.fit.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.Comment;
import com.fit.model.FitnessProgram;
import com.fit.model.Image;
import com.fit.model.User;
import com.fit.repo.CommentRepository;
import com.fit.repo.FitnessProgramRepository;
import com.fit.repo.UserRepository;

@Service
public class FitnessProgramService {
	private static final String FILTER_NAME = "fitnessProgramFilter";
	private static final String[] BASIC_PROPS = {"id", "name", "price", "images", "hidden"};
	
	@Autowired
	private FitnessProgramRepository fpRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CommentRepository commentRepo;
	
	public MappingJacksonValue addFitnessProgram(FitnessProgram fitnessProgram) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter(FILTER_NAME, 
						SimpleBeanPropertyFilter.filterOutAllExcept(BASIC_PROPS));
		
		MappingJacksonValue json = new MappingJacksonValue(
				fpRepo.save(fitnessProgram));
		json.setFilters(filterProvider);
		return json;
	}
	
	public ResponseEntity<List<byte[]>> getImages(int id) {
		FitnessProgram fitnessProgram = fpRepo.findById(id).get();
		List<byte[]> images = new ArrayList<>();
		
		for(Image image : fitnessProgram.getImages()) {
			RestTemplate rest = new RestTemplate();
			ResponseEntity<byte[]> response = rest.exchange(image.getPath(), HttpMethod.GET, null, byte[].class);
			if (response.getStatusCode() == HttpStatus.OK)
				images.add(response.getBody());
		}
		
		 return new ResponseEntity<>(images, HttpStatus.OK);
	}
	
	public MappingJacksonValue postComment(int id, Map<String, Comment> body) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("fitnessProgramFilter", 
						SimpleBeanPropertyFilter.serializeAllExcept("participations"));
		
		FitnessProgram fitnessProgram = fpRepo.findById(id).get();
		Comment comment = body.get("comment");
		User user = userRepo.findByUsername(comment.getPublishedBy().getUsername());
		
		Comment post = new Comment();
		post.setFitnessProgram(fitnessProgram);
		post.setPublishedBy(user);
		post.setContent(comment.getContent());
		post.setPublished(LocalDateTime.now());
		
		if (body.containsKey("replyTo")) {
			Comment temp = body.get("replyTo");
			
			Comment parentComment = new Comment();
			parentComment.setPublishedBy(userRepo.findByUsername(temp.getPublishedBy().getUsername()));
			parentComment.setFitnessProgram(fitnessProgram);
			parentComment.setPublished(temp.getPublished());
			
			post.setParentComment(parentComment);
		}
		
		commentRepo.save(post);
		
		MappingJacksonValue json = new MappingJacksonValue(fitnessProgram);
        json.setFilters(filterProvider);
        return json;
	}
}
