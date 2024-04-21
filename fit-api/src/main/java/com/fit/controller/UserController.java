package com.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.User;
import com.fit.repo.UserRepository;
import com.fit.service.UserService;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getUsers() {
		return userRepo.findAll();
	}
	
	@GetMapping("{username}")
	public User getUser(@PathVariable String username) {
		return userRepo.findByUsername(username);
	}
	
	@GetMapping("{username}/created-fitness-programs")
	public MappingJacksonValue getCreatedFitnessPrograms(@PathVariable String username) {
		return userService.getCreatedFitnessPrograms(username);
	}
	
	@GetMapping("{username}/fitness-program-participations")
	public MappingJacksonValue getFitnessProgramParticipations(@PathVariable String username) {
		return userService.getFitnessProgramParticipations(username);
	}
	
	@PostMapping("login")
	public User login(@RequestBody User user) {
		return userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	@PostMapping("add")
	public User createProfile(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@PutMapping("update")
	public User updateProfile(@RequestBody User user) {
		return userService.updateUser(user);
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
