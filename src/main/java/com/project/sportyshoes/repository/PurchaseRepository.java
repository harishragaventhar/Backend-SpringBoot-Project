package com.project.sportyshoes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.sportyshoes.entity.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
	List<Purchase> findByProductCategoryName(String id);
	
	List<Purchase> findByorderedDate(Date date);

	List<Purchase> findByUserId(int id);
}
