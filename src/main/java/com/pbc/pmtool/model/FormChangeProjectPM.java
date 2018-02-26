package com.pbc.pmtool.model;

import org.hibernate.validator.constraints.NotEmpty;

public class FormChangeProjectPM {
	
	@NotEmpty
	private String newPm;

	public String getNewPm() {
		return newPm;
	}

	public void setNewPm(String newPm) {
		this.newPm = newPm;
	}

	public FormChangeProjectPM(String newPm) {
		super();
		this.newPm = newPm;
	}
	
	public FormChangeProjectPM() {}
	
}
