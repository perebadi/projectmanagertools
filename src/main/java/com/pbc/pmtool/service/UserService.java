package com.pbc.pmtool.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pbc.pmtool.model.FormUserAdminModel;

/**
 * Servicio de usuarios
 * 
 * @author Josep Recio
 *
 */

public interface UserService extends UserDetailsService {

	/**
	 * Obtiene todos los usuarios de la base de datos
	 * 
	 * @return List<User>
	 */
	public List<FormUserAdminModel> getAllUsers(); 
	
}
