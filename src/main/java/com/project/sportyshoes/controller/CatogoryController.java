package com.project.sportyshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.sportyshoes.entity.Category;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;
import com.project.sportyshoes.service.CategoryService;

@Controller
@RestController
@RequestMapping("category")
public class CatogoryController {
	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute Category catogory) {
		ModelAndView mv = new ModelAndView();
		try {
			int res = categoryService.create(catogory);
			mv.setViewName("redirect:/admin/dashboard");
		} catch (DuplicateIdException e) {
			mv.setViewName("forward:/admin/dashboard");
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Category> loadAll() {
		List<Category> catogories = categoryService.findAll();
		return catogories;
	}

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public Object findByName(@PathVariable String name) {
		Category catogory;
		try {
			catogory = categoryService.find(name);
			return catogory;
		} catch (DataNotFoundException e) {
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/admin/dashboard");
		try {
			categoryService.delete(id);
		} catch (Exception e) {
			mv.addObject("Message", "Error in deleteing");
		}
		return mv;
	}

}
