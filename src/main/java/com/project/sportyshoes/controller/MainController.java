package com.project.sportyshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.sportyshoes.service.AdminService;

@Controller
public class MainController {

	@Autowired
	AdminService adminService;

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping("adminLogin")
	public String adminLogin() {
		return "adminLogin";
	}
}
