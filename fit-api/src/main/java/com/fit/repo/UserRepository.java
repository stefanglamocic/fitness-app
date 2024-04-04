package com.fit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	User findByUsername(String username);
}
