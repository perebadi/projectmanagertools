package com.pbc.pmtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.model.FormUserAdminModel;
import com.pbc.pmtool.service.UserService;

/**
 * Controlador de usuarios
 * 
 * @author Josep Recio
 *
 */

@Controller
@RequestMapping("/users")
public class UserController {

	/**
	 * Inyectamos las dependencias
	 */
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	/**
	 * Muestra los usuarios
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/show")
	public ModelAndView showUsers() {
		//Creamos la vista
		ModelAndView mav = new ModelAndView(ViewConstant.LIST_USERS);
		
		//Obtenemos todos los usuarios
		mav.addObject("users", userService.getAllUsers());
		
		//Devolvemos la vista
		return mav;
	}
	
	/**
	 * Guarda un usuario
	 * 
	 * @param formUserAdminModel
	 * @return redirect
	 */
	@PostMapping("/saveuser")
	public String saveUser(@ModelAttribute(name="user") FormUserAdminModel formUserAdminModel) {
		//Guardamos el usuario
		userService.saveUser(formUserAdminModel);
		
		//Redirigimos al listado de usuarios
		return "redirect:/users/show";
	}
}
