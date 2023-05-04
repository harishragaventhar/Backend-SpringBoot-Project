package com.project.sportyshoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sportyshoes.entity.Category;
import com.project.sportyshoes.entity.Product;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;
import com.project.sportyshoes.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	@Override
	public String create(Product product, String category_name) throws DuplicateIdException, DataNotFoundException {
		if (productRepository.existsByName(product.getName())) {
			return "Product with name " + product.getName() + " exists";
		}
		try {
			Category category = categoryService.find(category_name);
			product.setCategory(category);
			String id = productRepository.save(product).getName();
			return id;
		} catch (DataNotFoundException e) {
			throw new DataNotFoundException();
		}
	}

	@Override
	public List<Product> loadAll() {
		List<Product> products = (List<Product>) productRepository.findAll();
		return products;
	}

	@Override
	public List<Product> findProduct(String name) {
		List<Product> products = productRepository.findByName(name);
		return products;
	}

	@Override
	public List<Product> findProduct(String name, Boolean catogory) {
		List<Product> products = productRepository.findByCategoryName(name);
		return products;
	}

	@Override
	public String update(Product product) throws Exception {

		if (!productRepository.existsById(product.getId())) {
			throw new Exception("Invalid data please update with unique name or data not found for request id");
		} else {
			try {
				String name = productRepository.save(product).getName();
				return name;
			} catch (Exception e) {
				throw new Exception();
			}
		}
	}

	@Override
	public void delete(int id) throws Exception {

		if (!productRepository.existsById(id))
			throw new Exception("Product not found");

		else {

			productRepository.deleteById(id);
		}
	}

	@Override
	public Product findProduct(int id) {
		Product product = productRepository.findById(id);
		return product;
	}

}
