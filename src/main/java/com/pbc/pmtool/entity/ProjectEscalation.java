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
@Table(name = "projectescalation")
public class ProjectEscalation implements Comparable<ProjectEscalation> {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@NotNull
	@Column(name = "dateescalation")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateescalation;

	@Column(name = "week")
	private int week;

	@Column(name = "summaryescalation")
	private String summaryescalation;

	@Column(name = "txtescalation")
	private String txtescalation;

	@ManyToOne(fetch = FetchType.EAGER)
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateescalation() {
		return dateescalation;
	}

	public void setDateescalation(Date dateescalation) {
		this.dateescalation = dateescalation;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getSummaryescalation() {
		return summaryescalation;
	}

	public void setSummaryescalation(String summaryescalation) {
		this.summaryescalation = summaryescalation;
	}

	public String getTxtescalation() {
		return txtescalation;
	}

	public void setTxtescalation(String txtescalation) {
		this.txtescalation = txtescalation;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectEscalation(int id, Date dateescalation, int week, String summaryescalation, String txtescalation,
			Project project) {
		super();
		this.id = id;
		this.dateescalation = dateescalation;
		this.week = week;
		this.summaryescalation = summaryescalation;
		this.txtescalation = txtescalation;
		this.project = project;
	}

	public ProjectEscalation() {

	}

	@Override
	public int compareTo(ProjectEscalation o) {
		if(this.getDateescalation().before(o.getDateescalation())) {
			return -1;
		}else if(this.getDateescalation().after(o.getDateescalation())) {
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
