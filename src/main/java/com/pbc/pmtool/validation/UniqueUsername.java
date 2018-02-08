package com.pbc.pmtool.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pbc.pmtool.repository.UserRepository;

public class UniqueUsername implements ConstraintValidator<UniqueUsernameConstraint, String>  {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public void initialize(UniqueUsernameConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext arg1) {
		//Comprovamos si ya existe un usuario con este email
		if(userRepository.findByUsername(username) != null) {
			//Ya existe un usuario con este email
			return false;
		}
		
		//No existe un usuario con este email
		return true;
	}

}
