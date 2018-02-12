package com.pbc.pmtool.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.component.FormResetPasswordConverter;
import com.pbc.pmtool.component.FormUserAddConverter;
import com.pbc.pmtool.component.FormUserAdminConverter;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.entity.UserRole;
import com.pbc.pmtool.model.FormResetPasswordModel;
import com.pbc.pmtool.model.FormUserAddModel;
import com.pbc.pmtool.model.FormUserAdminModel;
import com.pbc.pmtool.model.LoginResetPasswordModel;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.UserService;






@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private FormUserAdminConverter userConverter;
	
	@Autowired
	@Qualifier("formUserAddConverter")
	private FormUserAddConverter userAddConverter;
	
	@Autowired
	@Qualifier("resetPasswordConverter")
	private FormResetPasswordConverter resetPasswordConverter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	private org.springframework.security.core.userdetails.User buildUser(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
				true, true, true, authorities);
		
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(UserRole  userRole : userRoles){
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
			
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	/**
	 * Devuelve todos los usuarios
	 */
	@Override
	public HashMap<String, Object> getAllUsers(Pageable pageable) {
		// Creamos el mapa
		HashMap<String, Object> allUsers = new HashMap<String, Object>();

		// Creamos el listado de usuarios
		ArrayList<FormUserAdminModel> usersModel = new ArrayList<FormUserAdminModel>();

		// Recorremos todos los usuarios
		for (User userEntity : userRepository.findAll(pageable).getContent()) {
			// Convertimos la entidad a modelo
			usersModel.add(userConverter.UserEntity2FormUserAdminModel(userEntity));
		}

		// Añadimos los usuarios al mapa
		allUsers.put("users", usersModel);

		// Añadimos la info
		allUsers.put("totalUsersPages", userRepository.findAll(pageable).getTotalPages() - 1);
		allUsers.put("totalUsers", userRepository.findAll(pageable).getTotalElements());

		// Devolvemos el listado de usuarios modelo
		return allUsers;
	}

	public HashMap<String, Object> getUsersByUsernameOrName(Pageable pageable, String username) {
		// Creamos el mapa
		HashMap<String, Object> allUsers = new HashMap<String, Object>();

		// Creamos el listado de usuarios
		ArrayList<FormUserAdminModel> usersModel = new ArrayList<FormUserAdminModel>();

		// Recorremos todos los usuarios
		for (User userEntity : userRepository
				.findUsersByUsernameContainingOrNameContaining(username, username, pageable).getContent()) {
			// Convertimos la entidad a modelo
			usersModel.add(userConverter.UserEntity2FormUserAdminModel(userEntity));
		}

		// Añadimos los usuarios al mapa
		allUsers.put("users", usersModel);

		// Añadimos la info
		allUsers.put("totalUsersPages", userRepository
				.findUsersByUsernameContainingOrNameContaining(username, username, pageable).getTotalPages() - 1);
		allUsers.put("totalUsers", userRepository
				.findUsersByUsernameContainingOrNameContaining(username, username, pageable).getTotalElements());

		// Devolvemos el listado de usuarios modelo
		return allUsers;
	}
	
	/**
	 * Guarda un usuario
	 */
	@Override
	public FormUserAdminModel saveUser(FormUserAdminModel saveUser) {
		//Guardamos el usuario
		User userSaved = userRepository.save(userConverter.FormUserAdminModel2UserEntity(saveUser));
		
		// TODO Auto-generated method stub
		return userConverter.UserEntity2FormUserAdminModel(userSaved);
	}

	/**
	 * Añade un usuario
	 */
	@Override
	public FormUserAddModel addUser(FormUserAddModel newUser) {
		//Creamos el nuevo usuario
		User userAdded = userRepository.save(userAddConverter.FormUserAddModel2User(newUser));
		
		//Devolvemos el usuario creado
		return userAddConverter.User2FormUserAddModel(userAdded);
	}
	
	/**
	 * Elimina un usuario
	 */
	@Override
	public void removeUser(String username) {
		//Comprovamos si existe el usuario en la BBDD
		if(userRepository.findByUsername(username) != null) {
			//Eliminamos el usuario de la BBDD
			userRepository.delete(username);
		}
	}

	/**
	 * Actualiza una contraseña
	 */
	@Override
	public FormResetPasswordModel resetPassword(FormResetPasswordModel resetPasswordModel) {
		//Actualizamos la contraseña
		User passwordResetEntity = userRepository.save(resetPasswordConverter.FormResetPasswordModel2User(resetPasswordModel));
		
		//Devolvemos el modelo
		return resetPasswordConverter.User2FormResetPasswordModel(passwordResetEntity);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}
	
	/**
	 * Guarda una nueva pasword generada
	 */
	@Override
	public LoginResetPasswordModel saveNewPassword(LoginResetPasswordModel resetPasswordModel) {
		// Actualizamos la contraseña
		User passwordResetEntity = userRepository
				.save(resetPasswordConverter.LoginResetPasswordModel2User(resetPasswordModel));

		// Devolvemos el modelo
		return resetPasswordConverter.User2LoginResetPasswordModel(passwordResetEntity);
	}
}
