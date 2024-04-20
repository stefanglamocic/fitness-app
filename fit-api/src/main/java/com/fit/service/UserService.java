package com.fit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.User;
import com.fit.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public MappingJacksonValue getCreatedFitnessPrograms(String username) {
		User user = userRepository.findByUsername(username);
		
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("fitnessProgramFilter",
						SimpleBeanPropertyFilter.filterOutAllExcept("id", "name"));
		
		MappingJacksonValue json = new MappingJacksonValue(
				user.getCreatedFitnessPrograms());
		json.setFilters(filterProvider);
		
		return json;
	}
	
	public User updateUser(User other) {
		User user = userRepository.findByUsername(other.getUsername());
		user.setName(other.getName());
		user.setSurname(other.getSurname());
		user.setCity(other.getCity());
		user.setMail(other.getMail());
		if (other.getPassword() != null && !"".equals(other.getPassword()))
			user.setPassword(other.getPassword());
		
		return userRepository.save(user);
	}
}
