package com.fit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.FitnessProgram;

public interface FitnessProgramRepository extends JpaRepository<FitnessProgram, Integer>{
	Optional<FitnessProgram> findById(Integer id);
	List<FitnessProgram> findByHiddenFalse();
}
