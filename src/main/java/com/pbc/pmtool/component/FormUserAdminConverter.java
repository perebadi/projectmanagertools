package com.pbc.pmtool.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.FormUserAdminModel;
import com.pbc.pmtool.repository.UserRepository;

/**
 * Convierte entidades usuario a modelos de administración de usuario y viceversa
 * 
 * @author Josep Recio
 *
 */

@Component("userConverter")
public class FormUserAdminConverter {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
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
		formUserAdminModel.setName(userEntity.getName());
		formUserAdminModel.setRate(userEntity.getRate());
		
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
		
		//Establecemos los atributos de la entidad existentes en el modelo
		userEntity.setUsername(formUserAdminModel.getUsername());
		userEntity.setEnabled(formUserAdminModel.isEnabled());
		userEntity.setName(formUserAdminModel.getName());
		userEntity.setRate(formUserAdminModel.getRate());
		
		//Obtenemos la entidad actual en la base de datos
		User userBBDD = userRepository.findByUsername(formUserAdminModel.getUsername());
		
		//Si existe en la base de datos
		if(userBBDD != null) {
			//Establecemos en la entidad los atributos que no hay en el modelo
			userEntity.setPassword(userBBDD.getPassword());
			userEntity.setProjects(userBBDD.getProjects());
			userEntity.setTasks(userBBDD.getTasks());
			userEntity.setUserRole(userBBDD.getUserRole());
		}
		
		//Devolvemos la entidad
		return userEntity;
	}
	
}
