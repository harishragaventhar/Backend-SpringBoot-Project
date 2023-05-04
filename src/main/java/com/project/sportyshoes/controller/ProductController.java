package com.project.sportyshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.sportyshoes.entity.Category;
import com.project.sportyshoes.entity.Product;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;
import com.project.sportyshoes.service.CategoryService;
import com.project.sportyshoes.service.ProductService;

@Controller
@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("product") Product product, @RequestParam String category_name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/dashboard");
		try {
			try {
				productService.create(product, category_name);

			} catch (DataNotFoundException e) {
				e.getMessage();
			}
		} catch (DuplicateIdException e) {
			e.getMessage();
		}
		return mv;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Product> findAll() {
		List<Product> products = productService.loadAll();
		return products;
	}

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public List<Product> findByName(@PathVariable String name) {
		List<Product> products = productService.findProduct(name);
		return products;
	}

	@RequestMapping(value = "category/{category_name}", method = RequestMethod.GET)
	public List<Product> findByCatogory(@PathVariable String category_name) {
		List<Product> products = productService.findProduct(category_name, true);
		return products;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("product") Product product) {
		ModelAndView mv = new ModelAndView();
		try {
			productService.update(product);
			mv.setViewName("redirect:/admin/dashboard");
		} catch (Exception e) {
			e.getMessage();
		}
		return mv;
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		Product product = productService.findProduct(id);
		mv.addObject("product", product);
		System.out.println(product);
		List<Category> categories = categoryService.findAll();
		mv.addObject("categories", categories);
		mv.setViewName("updateProduct");
		return mv;
	}

	@RequestMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/dashboard");
		try {
			productService.delete(id);
		} catch (Exception e) {
			mv.addObject("Message", "Error in deleteing");
		}
		return mv;
	}
}
