package com.pbc.pmtool.model;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pbc.pmtool.entity.UserRole;

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
	private boolean enabled;
	private float rate;
	@NotNull
	private Set<String> userRoles;
	private Set<UserRole> rolesAsigned;
	
	public FormUserAdminModel() {
	}

	public FormUserAdminModel(String username, String name, boolean enabled, float rate) {
		super();
		this.username = username;
		this.name = name;
		this.enabled = enabled;
		this.rate = rate;
	}
	
	/**
	 * Devuelve si el usuario tiene asignado un rol
	 * 
	 * @param role
	 * @return boolean
	 */
	public boolean hasRole(String role) {
		//Recorremos todos los roles del usuario
		for(UserRole userRoles : getRolesAsigned()) {
			//Comprovamos si el usuario tiene el rol
			if(userRoles.getRole().equals(role)) {
				//Tiene el rol
				return true;
			}
		}
		
		//No tiene el rol
		return false;
	}
	
	/**
	 * Devuelve el rol que tiene el usuario asignado
	 * 
	 * @param role
	 * @return UserRole
	 */
	public UserRole getRole(String role) {
		//Recorremos todos los roles del usuario
		for(UserRole userRoles : getRolesAsigned()) {
			//Comprovamos si el usuario tiene el rol
			if(userRoles.getRole().equals(role)) {
				//Devolvemos el rol
				return userRoles;
			}
		}
		
		//Devuelve nulo
		return null;
	}
	
	public Set<UserRole> getRolesAsigned() {
		return rolesAsigned;
	}

	public void setRolesAsigned(Set<UserRole> rolesAsigned) {
		this.rolesAsigned = rolesAsigned;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
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
