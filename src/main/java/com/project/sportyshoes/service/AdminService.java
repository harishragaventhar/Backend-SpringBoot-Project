package com.project.sportyshoes.service;

import javax.transaction.Transactional;

import com.project.sportyshoes.entity.Admin;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;

public interface AdminService {
	@Transactional
	int create(Admin admin) throws DuplicateIdException;

	Admin find(String email) throws DataNotFoundException;

	boolean exists(String email);

	Object find(String email, String password) throws DataNotFoundException;

	void changePassword(String email, String oldPassword, String newPassword) throws Exception;
}
