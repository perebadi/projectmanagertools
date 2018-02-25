package com.pbc.pmtool.model;

import org.hibernate.validator.constraints.NotEmpty;

public class FormSprint {
	
	@NotEmpty
	private String sprint;
	
	private int id;
	
	public FormSprint() {}
	
	public FormSprint(String sprint, int id) {
		super();
		this.sprint = sprint;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}
	
}
