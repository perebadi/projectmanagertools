package com.pbc.pmtool.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

public class FormCreateTaskModel {

	@NotNull
	private int projectid;
	
	@NotEmpty
	private String summary;
	
	@NotEmpty
	private String details;
	
	@NotNull
	private int time;
	
	@NotNull
	private int unit;
	
	private String username;

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getProjectid() {
		return projectid;
	}


	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public int getUnit() {
		return unit;
	}


	public void setUnit(int unit) {
		this.unit = unit;
	}

	
	public FormCreateTaskModel(int projectid, String summary, String details, int time, int unit, String username) {
		super();
		this.projectid = projectid;
		this.summary = summary;
		this.details = details;
		this.time = time;
		this.unit = unit;
		this.username = username;
	}


	public FormCreateTaskModel() {

	}

}
