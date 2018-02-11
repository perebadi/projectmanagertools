package com.pbc.pmtool.model;

public class FormAssignToProjectModel {

	private int projectid;
	private String username;

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public FormAssignToProjectModel(int projectid, String username) {
		super();
		this.projectid = projectid;
		this.username = username;
	}

	public FormAssignToProjectModel() {

	}

}
