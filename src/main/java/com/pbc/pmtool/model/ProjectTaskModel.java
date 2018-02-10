package com.pbc.pmtool.model;

import java.math.BigInteger;

public class ProjectTaskModel {
	  private int id;
	  private String projectname;
	  private String username;
	  private Boolean projectactive;
	  private BigInteger backlog;
	  private BigInteger todo;
	  private BigInteger inprogress;
	  private BigInteger done;

	


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getProjectname() {
		return projectname;
	}




	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public Boolean getProjectactive() {
		return projectactive;
	}




	public void setProjectactive(Boolean projectactive) {
		this.projectactive = projectactive;
	}




	public BigInteger getBacklog() {
		return backlog;
	}




	public void setBacklog(BigInteger backlog) {
		this.backlog = backlog;
	}




	public BigInteger getTodo() {
		return todo;
	}




	public void setTodo(BigInteger todo) {
		this.todo = todo;
	}




	public BigInteger getInprogress() {
		return inprogress;
	}




	public void setInprogress(BigInteger inprogress) {
		this.inprogress = inprogress;
	}




	public BigInteger getDone() {
		return done;
	}




	public void setDone(BigInteger done) {
		this.done = done;
	}




	public ProjectTaskModel(int id, String projectname, String username, Boolean projectactive, BigInteger backlog,
			BigInteger todo, BigInteger inprogress, BigInteger done) {
		super();
		this.id = id;
		this.projectname = projectname;
		this.username = username;
		this.projectactive = projectactive;
		this.backlog = backlog;
		this.todo = todo;
		this.inprogress = inprogress;
		this.done = done;
	}




	public ProjectTaskModel() {};
	  
}
