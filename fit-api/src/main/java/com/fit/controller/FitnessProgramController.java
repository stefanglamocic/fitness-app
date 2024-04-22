package com.fit.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.Comment;
import com.fit.model.FitnessProgram;
import com.fit.repo.FitnessProgramRepository;
import com.fit.service.FitnessProgramService;

@RestController
@RequestMapping(path = "/api/fitness-programs")
public class FitnessProgramController {
	private static final String[] includeProperties = {"id", "name", "price", "images", "hidden"};
	
	@Autowired
	private FitnessProgramRepository fitnessProgramRepo;
	@Autowired
	private FitnessProgramService service;
	
	
	@GetMapping("{id}/images")
	public ResponseEntity<List<byte[]>> getImages(@PathVariable Integer id) {
		return service.getImages(id);
	}
	
	@PostMapping("add")
	public MappingJacksonValue addFitnessProgram(@RequestBody FitnessProgram fitnessProgram) {
		return service.addFitnessProgram(fitnessProgram);
	}
	
	@GetMapping
	public MappingJacksonValue getItems(@RequestParam(defaultValue = "10") int items,
			@RequestParam(defaultValue="0") int offset) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("fitnessProgramFilter", 
						SimpleBeanPropertyFilter.filterOutAllExcept(includeProperties));
		MappingJacksonValue json = new MappingJacksonValue(
				fitnessProgramRepo.findByItemCountAndOffset(items, offset));
		json.setFilters(filterProvider);
		
		return json;
	}
	
	@GetMapping("{id}")
	public MappingJacksonValue getFitnessProgram(@PathVariable Integer id) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("fitnessProgramFilter", 
						SimpleBeanPropertyFilter.serializeAllExcept("participations"));
		
		FitnessProgram fitnessProgram = fitnessProgramRepo.findById(id).orElse(new FitnessProgram());
        List<Comment> filteredComments = fitnessProgram.getComments().stream()
        		.filter(comment -> comment.getParentComment() == null)
        		.collect(Collectors.toList());
        fitnessProgram.setComments(filteredComments);
        
        MappingJacksonValue json = new MappingJacksonValue(fitnessProgram);
        json.setFilters(filterProvider);
        return json;
	}
	
	@DeleteMapping("{id}")
	public void deleteFitnessProgram(@PathVariable Integer id) {
		FitnessProgram program = fitnessProgramRepo.findById(id).get();
		program.setHidden(true);
		fitnessProgramRepo.save(program);
	}
	
}
