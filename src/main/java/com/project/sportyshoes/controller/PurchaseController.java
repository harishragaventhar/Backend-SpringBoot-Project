package com.project.sportyshoes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.sportyshoes.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;

	@RequestMapping("buy/{productId}")
	public ModelAndView create(@PathVariable int productId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("UserEmail") != null) {
			String userEmail = (String) session.getAttribute("UserEmail");
			try {
				purchaseService.create(productId,userEmail);
				mv.setViewName("redirect:/user/dashboard");
			} catch (Exception e) {
				mv.setViewName("forward:/user/dashboard");
				mv.addObject("message", "Error in buying product");
			}
		}
		return mv;
	}
}
