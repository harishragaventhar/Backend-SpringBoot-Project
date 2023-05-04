package com.project.sportyshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.sportyshoes.entity.Admin;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;
import com.project.sportyshoes.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Override
	@Transactional
	public int create(Admin admin) throws DuplicateIdException {

		if (adminRepository.existsByEmail(admin.getEmail())) {
			throw new DuplicateIdException(admin.getEmail());
		} else {
			int id = adminRepository.save(admin).getId();
			return id;
		}
	}

	@Override
	public Admin find(String email) throws DataNotFoundException {
		Admin admin = adminRepository.findByEmail(email);
		if (admin == null) {
			throw new DataNotFoundException();
		}
		return admin;
	}

	@Override
	public boolean exists(String email) {

		return false;
	}

	@Override
	public Admin find(String email, String password) throws DataNotFoundException {
		System.out.println(email + " " + password);
		Admin admin = adminRepository.findByEmailAndPassword(email, password);
		if (admin == null) {
			throw new DataNotFoundException();
		}
		return admin;
	}

	@Override
	public void changePassword(String email, String oldPassword, String newPassword) throws Exception {
		
		Admin admin = adminRepository.findByEmail(email);
		if (oldPassword.equals(admin.getPassword())) {
			admin.setPassword(newPassword);
			adminRepository.save(admin);
		} else {
			throw new Exception("Invalid Password");
		}

	}
}
