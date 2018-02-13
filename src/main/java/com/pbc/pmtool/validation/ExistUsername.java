package com.pbc.pmtool.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pbc.pmtool.repository.UserRepository;

public class ExistUsername implements ConstraintValidator<ExistUsernameConstraint, String>  {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public void initialize(ExistUsernameConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext arg1) {
		//Comprovamos si existe un usuario con este email
		if(userRepository.findByUsername(username) != null) {
			//Existe un usuario con este email
			return true;
		}
		
		//No existe un usuario con este email
		return false;
	}

}
