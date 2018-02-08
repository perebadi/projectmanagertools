package com.pbc.pmtool.model;

import javax.validation.constraints.Size;

import com.pbc.pmtool.validation.MatchPasswordConstraint;
import com.pbc.pmtool.validation.UniqueUsernameConstraint;

/**
 * Modelo de creación de usuario
 * 
 * @author JEFE DE PROYECTO PM3
 *
 */

@MatchPasswordConstraint
public class FormUserAddModel extends MatchPasswordModel {

	/**
	 * Atributos
	 */
	@UniqueUsernameConstraint
	@Size(min=1, max=45)
	private String username;
	@Size(min=1, max=50)
	private String name;
	
	public FormUserAddModel() {
		super();
	}
	
	public FormUserAddModel(String username, String name) {
		super();
		this.username = username;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
