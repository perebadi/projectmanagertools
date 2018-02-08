package com.pbc.pmtool.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Modelo de administración usuario
 * 
 * @author JEFE DE PROYECTO PM3
 *
 */

public class FormUserAdminModel {

	/**
	 * Atributos
	 */
	@Size(min=1, max=45)
	private String username;
	@Size(min=1, max=50)
	private String name;
	@NotNull
	private boolean enabled;
	@NotNull
	private float rate;
	
	public FormUserAdminModel() {
	}

	public FormUserAdminModel(String username, String name, boolean enabled, float rate) {
		super();
		this.username = username;
		this.name = name;
		this.enabled = enabled;
		this.rate = rate;
	}
	
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
