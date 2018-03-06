package com.pbc.pmtool.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class FormProjectInfoModel {
	
	@NotEmpty
	private String projectname;
	
	@NotEmpty
	private String objectives;

	@NotEmpty
	private String wbs;
	
	@NotEmpty
	private String po;
	
	@Min(value=1)
	private int customerid;

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getWbs() {
		return wbs;
	}

	public void setWbs(String wbs) {
		this.wbs = wbs;
	}

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public FormProjectInfoModel(String projectname, String objectives, String wbs, String po, int customerid) {
		super();
		this.projectname = projectname;
		this.objectives = objectives;
		this.wbs = wbs;
		this.po = po;
		this.customerid = customerid;
	}
	
	public FormProjectInfoModel() {
		
	}
	
}


