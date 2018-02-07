package com.pbc.pmtool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	 * Muestra los usuarios
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/show")
	public ModelAndView showUsers() {
		return null;
	}
}
