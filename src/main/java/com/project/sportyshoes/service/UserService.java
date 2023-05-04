package com.project.sportyshoes.service;

import java.util.List;

import javax.transaction.Transactional;

import com.project.sportyshoes.entity.User;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;

public interface UserService {

	@Transactional
	String create(User user) throws DuplicateIdException;

	User getUser(String email, String password) throws DataNotFoundException;

	List<User> loadAll();
	
	User find(String email);

}
