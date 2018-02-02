package com.pbc.pmtool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pbc.pmtool.constant.ViewConstant;



@Controller
public class LoginController {	
	@GetMapping("/login")
	public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		
		System.out.println("*******************");

		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewConstant.LOGIN;
	}


	@GetMapping({"/loginsuccess","/","/logincheck"})
	public String loginCheck() {
		System.out.println("*******************");
		return "redirect:/projects/";
		
	}
}


