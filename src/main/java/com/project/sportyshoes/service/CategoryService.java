package com.project.sportyshoes.service;

import java.util.List;

import javax.transaction.Transactional;

import com.project.sportyshoes.entity.Category;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;

public interface CategoryService {
	@Transactional
	int create(Category catogory) throws DuplicateIdException;

	List<Category> findAll();

	Category find(String Name) throws DataNotFoundException;

	void delete(int id);
}
