package com.pbc.pmtool.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "projectnextstep")
public class ProjectNextStep implements Comparable<ProjectNextStep> {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "datenextstep")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datenextstep;
	
	@Column(name="week")
	private int week;
	
	@Column(name="summarynextstep")
	private String summarynextstep;
	
	@Column(name="txtnextstep")
	private String txtnextstep;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatenextstep() {
		return datenextstep;
	}

	public void setDatenextstep(Date datenextstep) {
		this.datenextstep = datenextstep;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getSummarynextstep() {
		return summarynextstep;
	}

	public void setSummarynextstep(String summarynextstep) {
		this.summarynextstep = summarynextstep;
	}

	public String getTxtnextstep() {
		return txtnextstep;
	}

	public void setTxtnextstep(String txtnextstep) {
		this.txtnextstep = txtnextstep;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectNextStep(int id, Date datenextstep, int week, String summarynextstep, String txtnextstep,
			Project project) {
		super();
		this.id = id;
		this.datenextstep = datenextstep;
		this.week = week;
		this.summarynextstep = summarynextstep;
		this.txtnextstep = txtnextstep;
		this.project = project;
	}
	
	public ProjectNextStep() {
		
	}

	@Override
	public int compareTo(ProjectNextStep o) {
		if(this.getDatenextstep().before(o.getDatenextstep())) {
			return -1;
		}else if(this.getDatenextstep().after(o.getDatenextstep())) {
			return 1;
		}else {
			if(this.getId() < o.getId()) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	
}
