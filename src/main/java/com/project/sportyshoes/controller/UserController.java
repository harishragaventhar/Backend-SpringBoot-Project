package com.project.sportyshoes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.project.sportyshoes.service.ProductService;
import com.project.sportyshoes.service.PurchaseService;
import com.project.sportyshoes.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	PurchaseService purchaseService;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") User user) {
		ModelAndView mv = new ModelAndView();
		try {
			userService.create(user);
			mv.setViewName("userLogin");
			mv.addObject("message", "Youre accout created login below");
			mv.addObject("type", "success");
			mv.addObject("message", "Your account is successfully created please login");
		} catch (DuplicateIdException e) {
			mv.setViewName("signUpUser");
			mv.addObject("message", "Account already exist please login");
		}
		return mv;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> loadAll() {
		return userService.loadAll();
	}

	@RequestMapping("login")
	public ModelAndView userLogin() {
		return new ModelAndView("userLogin");
	}

	@RequestMapping("signUp")
	public ModelAndView userSiginUp() {
		return new ModelAndView("signUpUser");
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request, ModelMap model) {
		User user;
		ModelAndView mv = new ModelAndView();
		try {
			user = (User) userService.getUser(email, password);
			HttpSession session = request.getSession();
			session.setAttribute("UserEmail", user.getEmail());
			model.addAttribute("user", user);
			mv.setViewName("redirect:/user/dashboard");
		} catch (DataNotFoundException e) {
			mv.addObject("message", "Invalid details");
			mv.addObject("type", "danger");
			mv.setViewName("userLogin");
		}
		return mv;
	}

	@RequestMapping("dashboard")
	public ModelAndView adminDashboard(ModelMap model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		String email = (String) session.getAttribute("UserEmail");
		if (session == null || email == null || !request.isRequestedSessionIdValid()) {
			mv.addObject("message", "Session is over please login again");
			mv.addObject("type", "danger");
			mv.setViewName("userLogin");
		} else {
			User user;
			List<Product> products = productService.loadAll();
			user = userService.find(email);
			List<Purchase> purchases = purchaseService.findByUser(user.getId());
			mv.addObject("products", products);
			mv.addObject("purchases", purchases);
			mv.addObject("user", user);
			mv.setViewName("userDashboard");
		}
		return mv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		session.invalidate();
		mv.addObject("message", "Logged out successfully");
		mv.addObject("type", "success");
		mv.setViewName("userLogin");
		return mv;
	}

}
