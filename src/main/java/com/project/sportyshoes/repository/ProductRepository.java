package com.project.sportyshoes.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.sportyshoes.entity.Category;
import com.project.sportyshoes.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	List<Product> findByCategory(Category category);

	boolean existsByName(String name);

	List<Product> findByName(String name);

	Product findById(int id);

	List<Product> findByCategoryName(String name);

	boolean existsById(int id);

	void deleteById(int id) throws Exception;
}
