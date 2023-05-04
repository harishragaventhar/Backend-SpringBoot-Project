package com.project.sportyshoes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.sportyshoes.entity.Admin;
import com.project.sportyshoes.entity.Category;
import com.project.sportyshoes.entity.Product;
import com.project.sportyshoes.entity.Purchase;
import com.project.sportyshoes.entity.User;
import com.project.sportyshoes.exception.DataNotFoundException;
import com.project.sportyshoes.exception.DuplicateIdException;
import com.project.sportyshoes.service.AdminService;
import com.project.sportyshoes.service.CategoryService;
import com.project.sportyshoes.service.ProductService;
import com.project.sportyshoes.service.PurchaseService;
import com.project.sportyshoes.service.UserService;

@Controller
@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService userService;

	@Autowired
	PurchaseService purchaseService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@ModelAttribute Admin admin) {
		int res;
		try {
			res = adminService.create(admin);
			return res + " Added";
		} catch (DuplicateIdException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "{email}", method = RequestMethod.GET)
	public Object find(@PathVariable String email) {
		Admin admin;
		try {
			admin = adminService.find(email);
			return admin;
		} catch (DataNotFoundException e) {
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView find(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request, ModelMap model) {
		Admin admin;
		ModelAndView mv = new ModelAndView();
		try {
			admin = (Admin) adminService.find(email, password);
			HttpSession session = request.getSession();
			session.setAttribute("email", admin.getEmail());
			model.addAttribute("admin", admin);
			mv.setViewName("redirect:/admin/dashboard");
			return mv;
		} catch (DataNotFoundException e) {
			mv.addObject("message", "Invalid details");
			mv.setViewName("adminLogin");
			return mv;
		}
	}

	@RequestMapping("dashboard")
	public ModelAndView adminDashboard(ModelMap model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("email");
		if (session == null || email == null || !request.isRequestedSessionIdValid()) {
			mv.addObject("message", "Session is over please login again");
			mv.setViewName("adminLogin");
		} else {
			Admin admin;
			try {
				List<Product> products = productService.loadAll();
				List<Category> categories = categoryService.findAll();
				List<User> users = userService.loadAll();
				List<Purchase> purchases = purchaseService.findAll();
				admin = adminService.find(email);
				mv.addObject("purchases", purchases);
				mv.addObject("products", products);
				mv.addObject("categories", categories);
				mv.addObject("admin", admin);
				mv.addObject("users", users);
				mv.setViewName("adminDashboard");
			} catch (DataNotFoundException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}

	@RequestMapping("changePassword")
	public ModelAndView changePassword(@RequestParam String oldPassword, @RequestParam String newPassword,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("email") == null || !request.isRequestedSessionIdValid()) {
			mv.addObject("message", "Session is over please login again");
			mv.setViewName("adminLogin");
		} else {
			String email = (String) session.getAttribute("email");
			try {
				adminService.changePassword(email, oldPassword, newPassword);
				mv.setViewName("redirect:/admin/dashboard");
			} catch (Exception e) {
				mv.addObject("message", "Please enter correct password");
				mv.addObject("type", "danger");
				mv.setViewName("forward:/admin/dashboard");
			}
		}

		return mv;
	}

	@RequestMapping("filter/category")
	public ModelAndView filterCategory(@RequestParam("category_name") String category_name) {
		ModelAndView mv = new ModelAndView();
		List<Purchase> purchases = purchaseService.findByCategory(category_name);
		System.out.println(purchases);
		mv.setViewName("forward:/admin/dashboard");
		mv.addObject("filteredPurchases",purchases);
		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		session.invalidate();
		mv.addObject("message", "Logged out successfully");
		mv.addObject("type", "success");
		mv.setViewName("adminLogin");
		return mv;
	}
}
