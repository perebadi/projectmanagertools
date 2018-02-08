package com.pbc.pmtool.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pbc.pmtool.model.FormResetPasswordModel;
import com.pbc.pmtool.model.FormUserAddModel;
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
	
	/**
	 * Guarda un modelo de usuario en la base de datos
	 */
	public FormUserAdminModel saveUser(FormUserAdminModel saveUser);
	
	/**
	 * Crea un nuevo usuario
	 * 
	 * @param newUser
	 * @return FormUserAddModel
	 */
	public FormUserAddModel addUser(FormUserAddModel newUser);
	
	/**
	 * Elimina un usuario
	 * 
	 * @return boolean
	 */
	public void removeUser(String username);
	
	/**
	 * Actualiza una contraseña
	 */
	public FormResetPasswordModel resetPassword(FormResetPasswordModel resetPasswordModel);
}
