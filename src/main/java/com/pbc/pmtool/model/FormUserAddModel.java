package com.pbc.pmtool.model;

import javax.validation.constraints.Size;

/**
 * Modelo de creación de usuario
 * 
 * @author JEFE DE PROYECTO PM3
 *
 */

public class FormUserAddModel {

	/**
	 * Atributos
	 */
	@Size(min=1, max=45)
	private String username;
	@Size(min=1, max=60)
	private String password;
	@Size(min=1, max=50)
	private String name;

	public FormUserAddModel() {}
	
	public FormUserAddModel(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
