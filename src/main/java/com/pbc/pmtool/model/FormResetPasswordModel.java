package com.pbc.pmtool.model;

import javax.validation.constraints.Size;

import com.pbc.pmtool.validation.MatchPasswordConstraint;
import com.pbc.pmtool.validation.UniqueUsernameConstraint;

@MatchPasswordConstraint
public class FormResetPasswordModel extends MatchPasswordModel {

	@Size(min = 1, max = 45)
	private String username;

	public FormResetPasswordModel() {
		super();
	}

	public FormResetPasswordModel(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
