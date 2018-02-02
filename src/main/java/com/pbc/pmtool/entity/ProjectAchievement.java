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
@Table(name = "projectachievement")
public class ProjectAchievement {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "dateachievement")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateachievement;
	
	@Column(name="week")
	private int week;
	
	@Column(name="summaryachievement")
	private String summaryachievement;
	
	@Column(name="txtachievement")
	private String txtachievement;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateachievement() {
		return dateachievement;
	}

	public void setDateachievement(Date dateachievement) {
		this.dateachievement = dateachievement;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getSummaryachievement() {
		return summaryachievement;
	}

	public void setSummaryachievement(String summaryachievement) {
		this.summaryachievement = summaryachievement;
	}

	public String getTxtachievement() {
		return txtachievement;
	}

	public void setTxtachievement(String txtachievement) {
		this.txtachievement = txtachievement;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectAchievement(int id, Date dateachievement, int week, String summaryachievement, String txtachievement,
			Project project) {
		super();
		this.id = id;
		this.dateachievement = dateachievement;
		this.week = week;
		this.summaryachievement = summaryachievement;
		this.txtachievement = txtachievement;
		this.project = project;
	}
	
	public ProjectAchievement() {
		
	}
	
	
	

}
