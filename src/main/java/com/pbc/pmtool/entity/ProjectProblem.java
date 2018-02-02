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
@Table(name = "projectproblem")
public class ProjectProblem {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "dateproblem")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateproblem;
	
	@Column(name="week")
	private int week;
	
	@Column(name="summaryproblem")
	private String summaryproblem;
	
	@Column(name="txtproblem")
	private String txtproblem;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateproblem() {
		return dateproblem;
	}

	public void setDateproblem(Date dateproblem) {
		this.dateproblem = dateproblem;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getSummaryproblem() {
		return summaryproblem;
	}

	public void setSummaryproblem(String summaryproblem) {
		this.summaryproblem = summaryproblem;
	}

	public String getTxtproblem() {
		return txtproblem;
	}

	public void setTxtproblem(String txtproblem) {
		this.txtproblem = txtproblem;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectProblem(int id, Date dateproblem, int week, String summaryproblem, String txtproblem,
			Project project) {
		super();
		this.id = id;
		this.dateproblem = dateproblem;
		this.week = week;
		this.summaryproblem = summaryproblem;
		this.txtproblem = txtproblem;
		this.project = project;
	}
	
	public ProjectProblem() {
		
	}

}
