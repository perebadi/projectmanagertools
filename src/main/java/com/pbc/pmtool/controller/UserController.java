package com.pbc.pmtool.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pbc.pmtool.constant.ViewConstant;
import com.pbc.pmtool.model.FormUserAdminModel;
import com.pbc.pmtool.service.ProjectService;
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
	
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	/**
	 * Muestra los usuarios
	 * 
	 * @return ModelAndView
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/show")
	public ModelAndView showUsers(@RequestParam(name="savesuccess", required=false) String savesucces,
			@RequestParam(name="saveerror", required=false) String saveerror, 
			@RequestParam(name="resetpasswordsuccess", required=false) String resetpasswordsuccess,
			@RequestParam(name="resetpassworderror", required=false) String resetpassworderror,
			@RequestParam(name="removesuccess", required=false) String removesuccess,
			@RequestParam(name="removeerror", required=false) String removeerror, 
			@RequestParam(name="page", required=false, defaultValue="0") int page, 
			@RequestParam(name="search", required=false, defaultValue="") String search) {
		
		//Creamos la vista
		ModelAndView mav = new ModelAndView(ViewConstant.LIST_USERS);
		
		//Indica los usuarios que se muestran por página
		int usersByPage = 3;
		
		//Pasamos los resultados de las acciones a la vista
		mav.addObject("saveerror", saveerror);
		mav.addObject("savesucces", savesucces);
		mav.addObject("resetpasswordsuccess", resetpasswordsuccess);
		mav.addObject("resetpassworderror", resetpassworderror);
		mav.addObject("removesuccess", removesuccess);
		mav.addObject("removeerror", removeerror);
		mav.addObject("page", page);
		mav.addObject("lastSearch", search);
		
		//Mapa de usuarios
		HashMap<String, Object> usuarios = new HashMap<String, Object>();
		
		//Obtenemos los usuarios
		if(search.equals("")) {
			usuarios = userService.getAllUsers(new PageRequest(page, usersByPage, Sort.Direction.ASC, "name"));
		}else {
			usuarios = userService.getUsersByUsernameOrName(
					new PageRequest(page, usersByPage, Sort.Direction.ASC, "name"), search);
		}
		
		//Pasamos a la vista los usuarrios
		mav.addObject("totalUsers", usuarios.get("totalUsers"));
		mav.addObject("totalUsersPages", usuarios.get("totalUsersPages"));
		mav.addObject("users", usuarios.get("users"));
		
		//Devolvemos la vista
		return mav;
	}
	
	/**
	 * Guarda un usuario
	 * 
	 * @param formUserAdminModel
	 * @return redirect
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/saveuser")
	public String saveUser(@Valid @ModelAttribute(name="user") FormUserAdminModel formUserAdminModel, 
			BindingResult bindingResult, 
			@RequestParam("page") int page, 
			@RequestParam("search") String search) {
		
		//Validamos si el usuario es valido
		if(!(bindingResult.hasErrors())) {	
			//Guardamos el usuario
			userService.saveUser(formUserAdminModel);
			
			//Redirigimos al listado de usuarios
			return "redirect:/users/show?page=" + page + "&search=" + search + "&savesuccess";
		}else {
			//Redirigimos al listado de usuarios
			return "redirect:/users/show?page=" + page + "&search=" + search + "&saveerror";
		}
	}
	
	
	/**
	 * Elimina un usuario
	 * 
	 * @return redirect
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/removeuser")
	public String removeUser(@RequestParam("username") String username,
			@RequestParam("page") int page,
			@RequestParam("search") String search) {
		
		//Validamos que el usuario a eliminar sea correcto
		if(!(username.equals(""))) {
			//Eliminamos el usuario
			userService.removeUser(username);
			
			//Redirigimos al listado de usuarios
			return "redirect:/users/show?page=" + page + "&search=" + search + "&removesuccess";
		}else {
			//Redirigimos con error
			return "redirect:/users/show?page=" + page + "&search=" + search + "&removeerror";
		}
	}
	
	
	
	
	
}
