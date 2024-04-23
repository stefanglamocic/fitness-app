package com.fit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.Image;

public interface ImageRepository extends JpaRepository<Image, String>{

}
