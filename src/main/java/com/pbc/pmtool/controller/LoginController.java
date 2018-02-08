package com.pbc.pmtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.model.FormUserAddModel;
import com.pbc.pmtool.service.UserService;



@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	/**
	 * Añade un nuevo usuario
	 */
	@PostMapping("/createuser")
	public String createUser(@Valid @ModelAttribute("user") FormUserAddModel newUser, BindingResult bindingResult, Model view) {
		//Comprovamos si nos han facilitado un usuario váido
		if(!(bindingResult.hasErrors())) {
			//Añadimos el usuario
			userService.addUser(newUser);
			
			//Redirigimos al form de login
			return "redirect:/login";
		}else {
			//Añadimos a la vista el modelo de nuevo usuario
			view.addAttribute("user", newUser);
			
			//Devolvemos la vista de alta de usuario
			return ViewConstant.CREATEACOUNT;
		}
	}
	
	/**
	 * Muestra el formulario para crear un usuario
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/createuser")
	public ModelAndView showCreateUser() {
		//Creamos la vista
		ModelAndView mav = new ModelAndView(ViewConstant.CREATEACOUNT);
		
		//Pasamos a la vista un nuevo modelo de creación de usuario
		mav.addObject("user", new FormUserAddModel());
		
		//Devolvemos la vista
		return mav;
	}
	
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


