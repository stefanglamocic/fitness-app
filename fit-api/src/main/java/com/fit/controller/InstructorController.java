package com.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fit.model.Instructor;
import com.fit.repo.InstructorRepository;

@RestController
@RequestMapping(path = "/api/instructors")
public class InstructorController {
	@Autowired
	private InstructorRepository repo;
	
	@GetMapping
	public List<Instructor> getAll() {
		return repo.findAll();
	}
}
