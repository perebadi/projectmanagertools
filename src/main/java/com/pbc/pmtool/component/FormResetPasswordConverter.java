package com.pbc.pmtool.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.FormResetPasswordModel;
import com.pbc.pmtool.model.LoginResetPasswordModel;
import com.pbc.pmtool.repository.UserRepository;

@Component("resetPasswordConverter")
public class FormResetPasswordConverter {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	public User LoginResetPasswordModel2User(LoginResetPasswordModel resetPasswordModel) {
		// Creamos la entidad
		User userEntity = new User();

		// Creamos el password encoder
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		// Asignamos los atributos a la entidad
		userEntity.setUsername(resetPasswordModel.getUsername());
		userEntity.setPassword(pe.encode(resetPasswordModel.getPassword()));

		// Obtenemos la entidad actual en la base de datos
		User userBBDD = userRepository.findByUsername(resetPasswordModel.getUsername());

		// Si existe en la base de datos
		if (userBBDD != null) {
			// Establecemos en la entidad los atributos que no hay en el modelo
			userEntity.setEnabled(userBBDD.isEnabled());
			userEntity.setName(userBBDD.getName());
			userEntity.setProjects(userBBDD.getProjects());
			userEntity.setTasks(userBBDD.getTasks());
			userEntity.setUserRole(userBBDD.getUserRole());
			userEntity.setRate(userBBDD.getRate());
		}

		// Devolvemos la entidad
		return userEntity;
	}

	public User FormResetPasswordModel2User(FormResetPasswordModel userResetModel) {
		// Creamos la entidad
		User userEntity = new User();

		// Creamos el password encoder
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		// Establecemos los atributos de la entidad existentes en el modelo
		userEntity.setUsername(userResetModel.getUsername());
		userEntity.setPassword(pe.encode(userResetModel.getPassword()));

		// Obtenemos la entidad actual en la base de datos
		User userBBDD = userRepository.findByUsername(userResetModel.getUsername());

		// Si existe en la base de datos
		if (userBBDD != null) {
			// Establecemos en la entidad los atributos que no hay en el modelo
			userEntity.setEnabled(userBBDD.isEnabled());
			userEntity.setName(userBBDD.getName());
			userEntity.setProjects(userBBDD.getProjects());
			userEntity.setTasks(userBBDD.getTasks());
			userEntity.setUserRole(userBBDD.getUserRole());
			userEntity.setRate(userBBDD.getRate());
		}

		// Devolvemos la entidad
		return userEntity;
	}

	public LoginResetPasswordModel User2LoginResetPasswordModel(User userEntity) {
		// Creamos el modelo
		LoginResetPasswordModel resetPasswordModel = new LoginResetPasswordModel();

		// Establecemos los atributos
		resetPasswordModel.setUsername(userEntity.getUsername());
		resetPasswordModel.setPassword(userEntity.getPassword());

		// Devolvemos el modelo
		return resetPasswordModel;
	}

	public FormResetPasswordModel User2FormResetPasswordModel(User userEntity) {
		// Creamos el modelo
		FormResetPasswordModel resetPasswordModel = new FormResetPasswordModel();

		// Establecemos los atributos
		resetPasswordModel.setUsername(userEntity.getUsername());
		resetPasswordModel.setConfirmPassword(userEntity.getPassword());
		resetPasswordModel.setPassword(userEntity.getPassword());

		// Devolvemos el modelo
		return resetPasswordModel;
	}
}
