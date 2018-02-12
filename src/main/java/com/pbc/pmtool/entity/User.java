package com.pbc.pmtool.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


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


	
	@ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
	@JoinTable(name = "project_user", 
		joinColumns = {@JoinColumn(name = "user_username", referencedColumnName = "username")}, 
		inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")})
	private List<Project> assigneds;

	
	
	
	

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






	public List<Project> getAssigneds() {
		return assigneds;
	}






	public void setAssigneds(List<Project> assigneds) {
		this.assigneds = assigneds;
	}






	public User(String username, String password, boolean enabled, String name, float rate, Set<UserRole> userRole,
			Set<Task> tasks, Set<Project> projects, List<Project> assigneds) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.rate = rate;
		this.userRole = userRole;
		this.tasks = tasks;
		this.projects = projects;
		this.assigneds = assigneds;
	}






	public User() {
	}

}
