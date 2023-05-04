package com.project.sportyshoes.service;

import java.text.ParseException;
import java.util.List;

import com.project.sportyshoes.entity.Purchase;

public interface PurchaseService {
	void create(int id,String userEmail);

	List<Purchase> findByUser(int id);
	
	List<Purchase> findAll();

	List<Purchase> findByCategory(String name);

	List<Purchase> findByDate(String date) throws ParseException;

}
