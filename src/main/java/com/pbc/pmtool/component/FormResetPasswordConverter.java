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
		// Obtenemos la entidad actual en la base de datos
		User userBBDD = userRepository.findByUsername(resetPasswordModel.getUsername());

		// Creamos el password encoder
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		// Asignamos los atributos a la entidad
		userBBDD.setPassword(pe.encode(resetPasswordModel.getPassword()));

		// Devolvemos la entidad
		return userBBDD;
	}

	public User FormResetPasswordModel2User(FormResetPasswordModel userResetModel) {
		// Obtenemos la entidad actual en la base de datos
		User userBBDD = userRepository.findByUsername(userResetModel.getUsername());

		// Creamos el password encoder
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

		// Establecemos los atributos de la entidad existentes en el modelo
		userBBDD.setPassword(pe.encode(userResetModel.getPassword()));

		// Devolvemos la entidad
		return userBBDD;
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
