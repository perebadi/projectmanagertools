package com.pbc.pmtool.component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.entity.UserRole;
import com.pbc.pmtool.model.FormUserAddModel;

/**
 * Convierte los modelos de creación de usuarios a entidades
 * 
 * @author JEFE DE PROYECTO PM3
 *
 */

@Component("formUserAddConverter")
public class FormUserAddConverter {

	/**
	 * Convierte un modelo de nuevo usuario a entidad usuario
	 * 
	 * @param userModel
	 * @return User
	 */
	public User FormUserAddModel2User(FormUserAddModel userModel) {
		//Creamos la entidad usuario
		User userEntity = new User();
		
		//Creamos el password encoder
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		
		//Creamos el rol USER para todos los usuarios por defecto
		UserRole userRole = new UserRole();
		
		userRole.setUser(userEntity);
		userRole.setRole("USER");
		
		//Creamos el set de roles para el nuevo usuario
		Set<UserRole> userRoles = new HashSet<UserRole>();
		
		//Añadimos los roles por defecto a la entidad usuario
		userRoles.add(userRole);
		
		//Establecemos los atributos de la entidad que se encuentran en el modelo
		userEntity.setName(userModel.getName());
		userEntity.setUsername(userModel.getUsername());
		userEntity.setPassword(pe.encode(userModel.getPassword()));
		
		//Establecemos los valores por defecto
		userEntity.setEnabled(true);
		userEntity.setUserRole(userRoles);
		userEntity.setRate(0);
		
		//Devolvemos la entidad usuario
		return userEntity;
	}
	
	public FormUserAddModel User2FormUserAddModel(User user) {
		//Creamos un modelo de nuevo usuario
		FormUserAddModel formUserAddModel = new FormUserAddModel();
		
		//Establecemos los atributos
		formUserAddModel.setName(user.getName());
		formUserAddModel.setUsername(user.getUsername());
		formUserAddModel.setPassword(user.getPassword());
		formUserAddModel.setConfirmPassword(user.getPassword());
		
		//Devolvemos el modelo de nuevo usuario
		return formUserAddModel;
	}
	
}
