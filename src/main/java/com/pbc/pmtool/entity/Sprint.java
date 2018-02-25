package com.pbc.pmtool.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sprint")
public class Sprint {

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Project project;
	
	@Column(name="sprint", nullable=false)
	private String sprint;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="sprint")
	private Set<Task> tasks;
	
	public Sprint(int id, Project project, String sprint, Set<Task> tasks) {
		super();
		this.id = id;
		this.project = project;
		this.sprint = sprint;
		this.tasks = tasks;
	}

	public Sprint() {}
	
	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}
	
}
