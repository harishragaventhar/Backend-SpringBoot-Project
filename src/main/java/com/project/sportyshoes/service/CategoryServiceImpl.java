package com.project.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sportyshoes.entity.Category;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;
import com.project.sportyshoes.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository catogoryRepository;

	@Override
	public int create(Category catogory) throws DuplicateIdException {
		if (catogoryRepository.existsByName(catogory.getName())) {
			throw new DuplicateIdException(catogory.getName());
		} else {
			return catogoryRepository.save(catogory).getId();
		}
	}

	@Override
	public List<Category> findAll() {
		List<Category> catogories = (List<Category>) catogoryRepository.findAll();
		return catogories;
	}

	@Override
	public Category find(String Name) throws DataNotFoundException {
		Category catogory = catogoryRepository.findByName(Name);
		if (catogory == null) {
			throw new DataNotFoundException();
		} else {
			return catogory;
		}
	}

	@Override
	public void delete(int id) {
		catogoryRepository.deleteById(id);
	}

}
