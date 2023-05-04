package com.project.sportyshoes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.sportyshoes.entity.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
	Admin findByEmail(String Email);

	boolean existsByEmail(String email);

	Admin findByEmailAndPassword(String email, String password);

}
