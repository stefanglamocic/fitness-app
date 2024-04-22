package com.fit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fit.model.FitnessProgram;

public interface FitnessProgramRepository extends JpaRepository<FitnessProgram, Integer>{
	Optional<FitnessProgram> findById(Integer id);
	List<FitnessProgram> findByHiddenFalse();
	@Query(value = "SELECT * FROM fitness_program WHERE hidden=0 ORDER BY id LIMIT ?1 OFFSET ?2", nativeQuery = true)
	List<FitnessProgram>findByItemCountAndOffset(int items, int offset);
}
