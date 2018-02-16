package com.pbc.pmtool.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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
	@NotEmpty
	private String username;
	@NotEmpty
	private String name;
	@NotNull
	private boolean enabled;
	@NotNull
	private float rate;
	@NotNull
	private Set<String> userRoles;
	
	private Set<UserRole> rolesAsigned;

	public FormUserAdminModel() {
	}

	public FormUserAdminModel(String username, String name, boolean enabled, float rate, Set<String> userRoles, Set<UserRole> rolesAsigned) {
		super();
		this.username = username;
		this.name = name;
		this.enabled = enabled;
		this.rate = rate;
		this.userRoles = userRoles;
		this.rolesAsigned = rolesAsigned;
	}

	/**
	 * Devuelve todos los roles de usuario disponible
	 * 
	 * @return Set<String>
	 */
	public Set<String> getAllUserRoles() {
		// Creamos el set con los roles posibles
		Set<String> roles = new HashSet<String>();

		// Añadimos los roles disponibles al set de roles
		roles.add("ROLE_SPECIALIST");
		roles.add("ROLE_PMO");
		roles.add("ROLE_PM");
		roles.add("ROLE_ADMIN");
		
		return roles;
	}

	/**
	 * Devuelve si el usuario tiene asignado un rol
	 * 
	 * @param role
	 * @return boolean
	 */
	public boolean hasRole(String role) {
		// Recorremos todos los roles del usuario
		for (UserRole userRoles : getRolesAsigned()) {
			// Comprovamos si el usuario tiene el rol
			if (userRoles.getRole().equals(role)) {
				// Tiene el rol
				return true;
			}
		}

		// No tiene el rol
		return false;
	}

	/**
	 * Devuelve el rol que tiene el usuario asignado
	 * 
	 * @param role
	 * @return UserRole
	 */
	public UserRole getRole(String role) {
		// Recorremos todos los roles del usuario
		for (UserRole userRoles : getRolesAsigned()) {
			// Comprovamos si el usuario tiene el rol
			if (userRoles.getRole().equals(role)) {
				// Devolvemos el rol
				return userRoles;
			}
		}

		// Devuelve nulo
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
