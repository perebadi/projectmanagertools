package com.pbc.pmtool.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.model.FormResetPasswordModel;
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
	 * Actualiza la password de los usuarios
	 * 
	 * @param resetPassword
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/resetpassword")
	public String resetPassword(@Valid @ModelAttribute("resetPassword") FormResetPasswordModel resetPassword, BindingResult bindingResult) {
		Logger.getGlobal().info("Username:" + resetPassword.getUsername());
		Logger.getGlobal().info("Pwd:" + resetPassword.getPassword());
		Logger.getGlobal().info("Confirm Pwd:" + resetPassword.getConfirmPassword());
		
		//Comprovamos que no hayan errores
		if(!(bindingResult.hasErrors())) {
			//Guardamos las credenciales nuevas
			userService.resetPassword(resetPassword);
			
			//Redirigimos
			return "redirect:/users/show?passwordchanged";
		}else {
			//Redirigimos
			return "redirect:/users/show?nopasswordmatches";
		}
	}
	
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
	public String saveUser(@Valid @ModelAttribute(name="user") FormUserAdminModel formUserAdminModel, BindingResult bindingResult) {
		//Validamos si el usuario es valido
		if(!(bindingResult.hasErrors())) {
			//Guardamos el usuario
			userService.saveUser(formUserAdminModel);
			
			//Redirigimos al listado de usuarios
			return "redirect:/users/show?usersaved";
		}else {
			//Redirigimos al listado de usuarios
			return "redirect:/users/show?errorusersaved";
		}
	}
	
	
	/**
	 * Elimina un usuario
	 * 
	 * @return redirect
	 */
	@PostMapping("/removeuser")
	public String removeUser(@ModelAttribute(name="user") FormUserAdminModel userDelete) {
		//Eliminamos el usuario
		userService.removeUser(userDelete.getUsername());
		
		//Redirigimos al listado de usuarios
		return "redirect:/users/show";
	}
}
