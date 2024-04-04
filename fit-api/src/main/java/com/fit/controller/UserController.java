package com.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.User;
import com.fit.repo.UserRepository;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@GetMapping
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	@GetMapping("{username}")
	public User getUser(@PathVariable String username) {
		return userRepo.findByUsername(username);
	}
	
	@GetMapping("{username}/inbox")
	public MappingJacksonValue getUserInbox(@PathVariable String username) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("messageFilter", 
						SimpleBeanPropertyFilter.serializeAllExcept("receiver"));
		
		MappingJacksonValue json = new MappingJacksonValue(
				getUser(username).getInbox());
		json.setFilters(filterProvider);
		
		return json;
	}
}
