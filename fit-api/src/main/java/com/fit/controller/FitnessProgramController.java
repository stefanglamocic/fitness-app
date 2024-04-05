package com.fit.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.Comment;
import com.fit.model.FitnessProgram;
import com.fit.repo.FitnessProgramRepository;

@RestController
@RequestMapping(path = "/api/fitness-programs")
public class FitnessProgramController {
	private static final String[] includeProperties = {"id", "name", "price", "images"};
	
	@Autowired
	FitnessProgramRepository fitnessProgramRepo;
	
	@GetMapping
	public MappingJacksonValue getAll() {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("fitnessProgramFilter", 
						SimpleBeanPropertyFilter.filterOutAllExcept(includeProperties));
		
		MappingJacksonValue json = new MappingJacksonValue(
				fitnessProgramRepo.findAll());
		json.setFilters(filterProvider);
		
		return json;
	}
	
	@GetMapping("{id}")
	public MappingJacksonValue getFitnessProgram(@PathVariable Integer id) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("fitnessProgramFilter", 
						SimpleBeanPropertyFilter.serializeAll());
		
		FitnessProgram fitnessProgram = fitnessProgramRepo.findById(id).orElse(new FitnessProgram());
        List<Comment> filteredComments = fitnessProgram.getComments().stream()
        		.filter(comment -> comment.getParentComment() == null)
        		.collect(Collectors.toList());
        fitnessProgram.setComments(filteredComments);
        
        MappingJacksonValue json = new MappingJacksonValue(fitnessProgram);
        json.setFilters(filterProvider);
        return json;
	}
}
