package com.project.sportyshoes.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.sportyshoes.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmailAndPassword(String email, String password);

	boolean existsByEmail(String emai);
	
	User findByEmail(String email);
}
