package com.pbc.pmtool.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

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
import com.pbc.pmtool.model.LoginResetPasswordModel;
import com.pbc.pmtool.service.EmailService;
import com.pbc.pmtool.service.UserService;



@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("emailService")
	private EmailService emailServiceImpl;
	
	/**
	 * Muestra el formulario para reiniciar el password
	 * 
	 * @return ModelAndView
	 */
	@GetMapping("/resetpassword")
	public ModelAndView showResetPassword() {
		// Creamos la vista
		ModelAndView mav = new ModelAndView(ViewConstant.RESETPASSWORD);

		//Asignamos a la vista un nuevo modelo LoginResetPassword
		mav.addObject("resetPassword", new LoginResetPasswordModel());
		
		// Devolvemos la vista
		return mav;
	}

	/**
	 * Actualiza el password del usuario
	 * 
	 * @param resetPassword
	 * @return ModelAndView
	 */
	@PostMapping("/resetpassword")
	public ModelAndView resetPassword(@Valid @ModelAttribute("resetPassword") LoginResetPasswordModel resetPassword,
			BindingResult bindingResult) {
		
		//Creamos la vista
		ModelAndView mav = new ModelAndView(ViewConstant.RESETPASSWORD);
			
		//Comprovamos si hemos recibido un modelo de reset de password válido
		if(bindingResult.hasErrors()) {
			//Asignamos el modelo con errores a la vista
			mav.addObject("resetPassword", resetPassword);
		}else {
			//Generamos la password aleatoriamente
		    String generatedString = RandomStringUtils.random(10, true, true);
		    
		    //Guardamos en el modelo de reset password la pwd generada
		    resetPassword.setPassword(generatedString);
			
			//Guardamos la nueva contraseña
			userService.saveNewPassword(resetPassword);
			
		    //Enviem email
		    emailServiceImpl.sendEmail("The new password for the " + resetPassword.getUsername() + " account is " + generatedString, 
		    		"Your account password has been reset", resetPassword.getUsername());
		    
			//Atributos de la vista
			mav.addObject("resetusername", resetPassword.getUsername());
			
			//Creamos un nuevo modelo de reset de password
			mav.addObject("resetPassword", new LoginResetPasswordModel());
		}
		
		//Devolvemos la vista
		return mav;
	}
	
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
			return "redirect:/login?newaccountsuccess=" + newUser.getUsername();
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
			@RequestParam(name = "logout", required = false) String logout,
			@RequestParam(name = "newaccountsuccess", required = false) String newaccountsuccess) {
		
		System.out.println("*********E**********");

		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		model.addAttribute("newaccountsuccess", newaccountsuccess);
		
		return ViewConstant.LOGIN;
	}


	@GetMapping({"/loginsuccess","/","/logincheck"})
	public String loginCheck() {
		System.out.println("*********AQUI**********");
		return "redirect:/projects/";
		
	}
}


