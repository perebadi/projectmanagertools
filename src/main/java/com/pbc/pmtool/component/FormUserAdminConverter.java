package com.pbc.pmtool.component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.entity.UserRole;
import com.pbc.pmtool.model.FormUserAdminModel;
import com.pbc.pmtool.repository.UserRepository;

/**
 * Convierte entidades usuario a modelos de administración de usuario y
 * viceversa
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
		// Creamos el modelo de usuario
		FormUserAdminModel formUserAdminModel = new FormUserAdminModel();

		// Establecemos los atributos del modelo
		formUserAdminModel.setUsername(userEntity.getUsername());
		formUserAdminModel.setEnabled(userEntity.isEnabled());
		formUserAdminModel.setName(userEntity.getName());
		formUserAdminModel.setRate(userEntity.getRate());
		formUserAdminModel.setRolesAsigned(userEntity.getUserRole());

		// Devolvemos el modelo de administración del usuario
		return formUserAdminModel;
	}

	/**
	 * Devuelve una entidad de usuario
	 * 
	 * @param formUserAdminModel
	 * @return User
	 */
	public User FormUserAdminModel2UserEntity(FormUserAdminModel formUserAdminModel) {
		User userBBDD = userRepository.findByUsername(formUserAdminModel.getUsername());

		// Establecemos los atributos de la entidad existentes en el modelo
		userBBDD.setUsername(formUserAdminModel.getUsername());
		userBBDD.setEnabled(formUserAdminModel.isEnabled());
		userBBDD.setName(formUserAdminModel.getName());
		userBBDD.setRate(formUserAdminModel.getRate());

		// Creamos un set con los roles
		Set<UserRole> userRoles = new HashSet<UserRole>();

		// Recorremos los roles que tiene el modelo
		for (String roleModel : formUserAdminModel.getUserRoles()) {
			// Comprovamos si la entidad tiene el rol
			if (userBBDD.hasRole(roleModel)) {
				// Añadimos el rol de la entidad la set de roles
				userRoles.add(userBBDD.getRole(roleModel));
			} else {
				// Creamos el rol
				UserRole userRole = new UserRole();

				// Asignamos los valores al role
				userRole.setUser(userBBDD);
				userRole.setRole(roleModel);

				// Añadimos el rol al set de roles
				userRoles.add(userRole);
			}
		}

		// Limpiamos los roles
		userBBDD.getUserRole().clear();

		// Asignamos a la entidad los roles
		userBBDD.getUserRole().addAll(userRoles);

		// Devolvemos la entidad
		return userBBDD;
	}

}
