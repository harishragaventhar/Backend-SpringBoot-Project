package com.project.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sportyshoes.entity.User;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;
import com.project.sportyshoes.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public String create(User user) throws DuplicateIdException {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new DuplicateIdException(user.getEmail());
		} else {
			String name = userRepository.save(user).getName();
			return name;
		}
	}

	@Override
	public User getUser(String email, String password) throws DataNotFoundException {
		User user = userRepository.findByEmailAndPassword(email, password);
		if (user == null) {
			throw new DataNotFoundException();
		} else {
			return user;
		}
	}

	@Override
	public List<User> loadAll() {
		List<User> users = (List<User>) userRepository.findAll();
		return users;
	}

	@Override
	public User find(String email) {
		return userRepository.findByEmail(email);
	}

}
