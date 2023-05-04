package com.project.sportyshoes.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sportyshoes.entity.Purchase;
import com.project.sportyshoes.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Override
	public void create(int id, String email) {
		Purchase purchase = new Purchase(userService.find(email), productService.findProduct(id));
		purchaseRepository.save(purchase);
	}

	@Override
	public List<Purchase> findByUser(int id) {
		return purchaseRepository.findByUserId(id);
	}

	@Override
	public List<Purchase> findByCategory(String name) {
		return purchaseRepository.findByProductCategoryName(name);
	}

	@Override
	public List<Purchase> findByDate(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date formatedDate;
		try {
			formatedDate = dateFormat.parse(date);
			List<Purchase> purchases = purchaseRepository.findByorderedDate(formatedDate);
			return purchases;
		} catch (ParseException e) {
			throw new ParseException(date, 0);
		}
	}

	@Override
	public List<Purchase> findAll() {

		return (List<Purchase>) purchaseRepository.findAll();
	}

}
