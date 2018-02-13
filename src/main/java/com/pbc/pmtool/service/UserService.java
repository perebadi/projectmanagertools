package com.pbc.pmtool.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.FormResetPasswordModel;
import com.pbc.pmtool.model.FormUserAddModel;
import com.pbc.pmtool.model.FormUserAdminModel;
import com.pbc.pmtool.model.LoginResetPasswordModel;

/**
 * Servicio de usuarios
 * 
 * @author Josep Recio
 *
 */

public interface UserService extends UserDetailsService {

	public HashMap<String, Object> getUsersByUsernameOrName(Pageable pageable, String username);
	
	/**
	 * Obtiene todos los usuarios de la base de datos
	 * 
	 * @return List<User>
	 */
	public HashMap<String, Object> getAllUsers(Pageable pageable); 
	
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
	
	
	/** Get User By Username*/
	public User getUser (String username);
	
	/** Save User*/
	public abstract User addUser(User user);
	
	/**
	 * Guarda la contraseña reiniciada
	 */
	public LoginResetPasswordModel saveNewPassword(LoginResetPasswordModel resetPasswordModel);

}
