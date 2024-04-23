package com.fit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fit.model.Image;
import com.fit.repo.ImageRepository;

@RestController
@RequestMapping(path = "/api/images")
public class ImageController {
	@Autowired
	private ImageRepository repo;
	
	@PostMapping
	public void uploadImages(@RequestBody List<Image> images) {
		images.stream()
			.filter(image -> !repo.existsById(image.getPath()))
			.forEach(image -> repo.save(image));
	}
}
