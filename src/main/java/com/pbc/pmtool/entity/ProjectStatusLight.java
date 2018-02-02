package com.pbc.pmtool.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "projectstatuslight")
public class ProjectStatusLight {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name="statusname")
	private String statusname;
	
	@Column(name="statusdescription")
	private String statusdescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getStatusdescription() {
		return statusdescription;
	}

	public void setStatusdescription(String statusdescription) {
		this.statusdescription = statusdescription;
	}

	public ProjectStatusLight(int id, String statusname, String statusdescription) {
		super();
		this.id = id;
		this.statusname = statusname;
		this.statusdescription = statusdescription;
	}

	public ProjectStatusLight() {
	}
	

}
