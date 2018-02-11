package com.pbc.pmtool.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "rate", nullable = false)
	private float rate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> userRole = new HashSet<UserRole>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Task> tasks = new HashSet<Task>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	private Set<Project> projects = new HashSet<Project>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "project_user", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"), inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	private Set<Project> assigneds;

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public User(String username, String password, boolean enabled, String name, float rate, Set<UserRole> userRole,
			Set<Task> tasks, Set<Project> projects) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.rate = rate;
		this.userRole = userRole;
		this.tasks = tasks;
		this.projects = projects;
	}

	public User() {
	}

}
