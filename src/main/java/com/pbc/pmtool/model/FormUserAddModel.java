package com.pbc.pmtool.model;

import org.hibernate.validator.constraints.NotEmpty;

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
	@NotEmpty
	private String username;
	@NotEmpty
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
