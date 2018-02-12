package com.pbc.pmtool.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.pbc.pmtool.validation.ExistUsernameConstraint;

public class LoginResetPasswordModel {

	@ExistUsernameConstraint
	@NotEmpty
	@Size(min=1, max=45)
	private String username;
	private String password;
	
	public LoginResetPasswordModel() {
	}

	public LoginResetPasswordModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
