package com.pbc.pmtool.model;

public class FormCreateTaskModel {

	private int projectid;
	private String username;
	private String summary;
	private String details;
	private int time;
	private int unit;

	
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

	
	

	public FormCreateTaskModel(int projectid, String username, String summary, String details, int time, int unit) {
		super();
		this.projectid = projectid;
		this.username = username;
		this.summary = summary;
		this.details = details;
		this.time = time;
		this.unit = unit;
	}


	public FormCreateTaskModel() {

	}

}
