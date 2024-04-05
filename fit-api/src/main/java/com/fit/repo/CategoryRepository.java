package com.fit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
