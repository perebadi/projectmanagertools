package com.pbc.pmtool.model;

import org.hibernate.validator.constraints.NotEmpty;

public class MatchPasswordModel {
	
	@NotEmpty(message="This field is missing")
	private String confirmPassword;
	@NotEmpty(message="This field is missing")
	private String password;

	public MatchPasswordModel() {
		super();
	}

	public MatchPasswordModel(String confirmPassword, String password) {
		super();
		this.confirmPassword = confirmPassword;
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
