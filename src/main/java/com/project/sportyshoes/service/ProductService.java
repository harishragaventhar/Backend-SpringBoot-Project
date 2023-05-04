package com.project.sportyshoes.service;

import java.util.List;

import javax.transaction.Transactional;

import com.project.sportyshoes.entity.Product;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;

public interface ProductService {
	@Transactional
	String create(Product product, String catogory_name) throws DuplicateIdException, DataNotFoundException;

	List<Product> loadAll();

	List<Product> findProduct(String name);
	
	Product findProduct(int name);

	List<Product> findProduct(String catogory_name, Boolean catogory);

	String update(Product product) throws Exception;

	void delete(int id) throws Exception;

}
