package com.project.sportyshoes.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.project.sportyshoes.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	Category findByName(String Name);

	boolean existsByName(String Name);

	void deleteById(int id);
}
