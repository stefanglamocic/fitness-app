package com.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
