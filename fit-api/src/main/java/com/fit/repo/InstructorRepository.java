package com.fit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

}
