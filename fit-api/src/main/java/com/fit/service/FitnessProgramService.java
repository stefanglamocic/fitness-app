package com.fit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.FitnessProgram;
import com.fit.repo.FitnessProgramRepository;

@Service
public class FitnessProgramService {
	private static final String FILTER_NAME = "fitnessProgramFilter";
	private static final String[] BASIC_PROPS = {"id", "name", "price", "images", "hidden"};
	
	@Autowired
	private FitnessProgramRepository fpRepo;
	
	public MappingJacksonValue addFitnessProgram(FitnessProgram fitnessProgram) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter(FILTER_NAME, 
						SimpleBeanPropertyFilter.filterOutAllExcept(BASIC_PROPS));
		
		MappingJacksonValue json = new MappingJacksonValue(
				fpRepo.save(fitnessProgram));
		json.setFilters(filterProvider);
		return json;
	}
}
