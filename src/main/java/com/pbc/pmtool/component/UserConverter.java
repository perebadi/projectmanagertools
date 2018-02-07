package com.pbc.pmtool.component;

import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.FormUserAdminModel;

/**
 * Convierte entidades usuario a modelos de administración de usuario y viceversa
 * 
 * @author Josep Recio
 *
 */

@Component("userConverter")
public class UserConverter {

	/**
	 * Devuelve un modelo de administración de usuario
	 * 
	 * @return FormUserAdminModel
	 */
	public FormUserAdminModel UserEntity2FormUserAdminModel(User userEntity) {
		//Creamos el modelo de usuario
		FormUserAdminModel formUserAdminModel = new FormUserAdminModel();
		
		//Establecemos los atributos del modelo
		formUserAdminModel.setUsername(userEntity.getUsername());
		formUserAdminModel.setEnabled(userEntity.isEnabled());
		
		//Devolvemos el modelo de administración del usuario
		return formUserAdminModel;
	}
	
	/**
	 * Devuelve una entidad de usuario
	 * 
	 * @param formUserAdminModel
	 * @return User
	 */
	public User FormUserAdminModel2UserEntity(FormUserAdminModel formUserAdminModel) {
		//Creamos la entidad
		User userEntity = new User();
		
		//Establecemos los atributos de la entidad
		userEntity.setUsername(formUserAdminModel.getUsername());
		userEntity.setEnabled(formUserAdminModel.isEnabled());
		
		//Devolvemos la entidad
		return userEntity;
	}
	
}
