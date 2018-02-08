package com.pbc.pmtool.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pbc.pmtool.model.MatchPasswordModel;

public class MatchPassword implements ConstraintValidator<MatchPasswordConstraint, Object> {

	@Override
	public boolean isValid(Object newUser, ConstraintValidatorContext arg1) {
		// Comprovamos si las passwords coinciden
		if (((MatchPasswordModel) newUser).getPassword().equals(((MatchPasswordModel) newUser).getConfirmPassword())) {
			// Las contraseñas coinciden
			return true;
		} else {
			// Las contraseñas no coinciden
			return false;
		}
	}

	@Override
	public void initialize(MatchPasswordConstraint matchPasswordConstraint) {
	}

}
