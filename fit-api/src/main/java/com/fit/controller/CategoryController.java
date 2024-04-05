package com.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fit.model.Category;
import com.fit.repo.CategoryRepository;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepo;
	
	@GetMapping
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}
}
