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
import org.springframework.web.bind.annotation.RequestParam;
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
		//Comprovamos que no hayan errores
		if(!(bindingResult.hasErrors())) {
			//Guardamos las credenciales nuevas
			userService.resetPassword(resetPassword);
			
			//Redirigimos
			return "redirect:/users/show?resetpasswordsuccess";
		}else {
			//Redirigimos
			return "redirect:/users/show?resetpassworderror";
		}
	}
	
	/**
	 * Muestra los usuarios
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/show")
	public ModelAndView showUsers(@RequestParam(name="savesuccess", required=false) String savesucces,
			@RequestParam(name="saveerror", required=false) String saveerror, 
			@RequestParam(name="resetpasswordsuccess", required=false) String resetpasswordsuccess,
			@RequestParam(name="resetpassworderror", required=false) String resetpassworderror,
			@RequestParam(name="removesuccess", required=false) String removesuccess,
			@RequestParam(name="removeerror", required=false) String removeerror) {
		//Creamos la vista
		ModelAndView mav = new ModelAndView(ViewConstant.LIST_USERS);
		
		//Pasamos los resultados de las acciones a la vista
		mav.addObject("saveerror", saveerror);
		mav.addObject("savesucces", savesucces);
		mav.addObject("resetpasswordsuccess", resetpasswordsuccess);
		mav.addObject("resetpassworderror", resetpassworderror);
		mav.addObject("removesuccess", removesuccess);
		mav.addObject("removeerror", removeerror);
		
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
		//Validamos si el usuario es valido
		//if(!(bindingResult.hasErrors())) {
			if(formUserAdminModel.isEnabled()) {
				Logger.getGlobal().info("activo");
			}else {
				Logger.getGlobal().info("no activo");
			}
			
			//Guardamos el usuario
			userService.saveUser(formUserAdminModel);
			
			//Redirigimos al listado de usuarios
			return "redirect:/users/show?savesuccess";
		//}else {
			//Redirigimos al listado de usuarios
		//	return "redirect:/users/show?saveerror";
		//}
	}
	
	
	/**
	 * Elimina un usuario
	 * 
	 * @return redirect
	 */
	@PostMapping("/removeuser")
	public String removeUser(@Valid @ModelAttribute(name="user") FormUserAdminModel userDelete, BindingResult bindingResult) {
		//Validamos que el usuario a eliminar sea correcto
		if(!(bindingResult.hasErrors())) {
			//Eliminamos el usuario
			userService.removeUser(userDelete.getUsername());
			
			//Redirigimos al listado de usuarios
			return "redirect:/users/show?removesuccess";
		}else {
			//Redirigimos con error
			return "redirect:/users/show?removeerror";
		}
	}
}
