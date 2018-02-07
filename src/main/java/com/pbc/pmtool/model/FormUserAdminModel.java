package com.pbc.pmtool.model;

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
	private String username;
	private boolean enabled;

	public FormUserAdminModel() {
	}

	public FormUserAdminModel(String username, boolean enabled) {
		super();
		this.username = username;
		this.enabled = enabled;
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
