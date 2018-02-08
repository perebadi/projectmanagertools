package com.pbc.pmtool.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



	@Entity
	@Table(name = "user")
	public class User {
		@Id
		@Column(name = "username", unique=true, nullable=false, length=45)
		private String username;

		@Column(name = "password", nullable=false, length=60)
		private String password;

		@Column(name = "enabled",nullable=false)
		private boolean enabled;
		
		@Column(name = "name", nullable=false, length=50)
		private String name;
		
		@Column(name = "rate", nullable=true)
		private float rate;
		
		@OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
		private Set<UserRole> userRole = new HashSet<UserRole>();
		
		@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
		private Set<Project> projects = new HashSet<Project>();
		
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
		private Set<Task> tasks = new HashSet<Task>();
		
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

		public Set<UserRole> getUserRole() {
			return userRole;
		}

		public void setUserRole(Set<UserRole> userRole) {
			this.userRole = userRole;
		}

		public Set<Project> getProjects() {
			return projects;
		}

		public void setProjects(Set<Project> projects) {
			this.projects = projects;
		}

		public Set<Task> getTasks() {
			return tasks;
		}

		public void setTasks(Set<Task> tasks) {
			this.tasks = tasks;
		}
		
		public float getRate() {
			return rate;
		}

		public void setRate(float rate) {
			this.rate = rate;
		}

		public User(String username, String password, boolean enabled, Set<UserRole> userRole, Set<Project> projects,
				Set<Task> tasks, String name, float rate) {
			super();
			this.username = username;
			this.password = password;
			this.enabled = enabled;
			this.userRole = userRole;
			this.projects = projects;
			this.tasks = tasks;
			this.name = name;
			this.rate = rate;
		}

		public User(){}
		
}
