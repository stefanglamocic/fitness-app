package com.fit.service;

import java.util.ArrayList;
import java.util.List;

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
import com.fit.model.FitnessProgram;
import com.fit.model.Image;
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
}
