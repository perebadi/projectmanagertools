	package com.pbc.pmtool.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "task")
public class Task {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "details", columnDefinition = "TEXT")
	private String details;
	
	@NotNull
	@Column(name = "dateacreation")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datecreation;
	
	@NotNull
	@Column(name = "datestatus")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datestatus;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
	private Set<Comment> comments = new HashSet<Comment>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	
	@Column(name = "estimatedhours")
	private int estimatedhours;
	
	@Column(name = "hours")
	private int hours;
	
	@Column(name = "status")
	private int status;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public Date getDatestatus() {
		return datestatus;
	}

	public void setDatestatus(Date datestatus) {
		this.datestatus = datestatus;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getEstimatedhours() {
		return estimatedhours;
	}

	public void setEstimatedhours(int estimatedhours) {
		this.estimatedhours = estimatedhours;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	public Task(int id, String summary, String details, Date datecreation, Date datestatus, Project project,
			Set<Comment> comments, User user, int estimatedhours, int hours, int status) {
		super();
		this.id = id;
		this.summary = summary;
		this.details = details;
		this.datecreation = datecreation;
		this.datestatus = datestatus;
		this.project = project;
		this.comments = comments;
		this.user = user;
		this.estimatedhours = estimatedhours;
		this.hours = hours;
		this.status = status;
	}

	public Task() {
		
	}
	
	
	
}



