package com.pbc.pmtool.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "projectphase")
public class ProjectPhase {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name="summaryphase")
	private String summaryphase;
	
	@NotNull
	@Column(name = "startdate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startdate;
	
	@NotNull
	@Column(name = "enddate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enddate;
	
	@Column(name = "weekdelay")
	private int weekdelay;
	
	@Column(name = "newdate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date newdate;
	
	@Column(name = "progress")
	private int progress;
	
/*	@Column(name = "rag")
*/	@OneToOne(fetch=FetchType.LAZY)
	private ProjectStatusLight rag;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSummaryphase() {
		return summaryphase;
	}

	public void setSummaryphase(String summaryphase) {
		this.summaryphase = summaryphase;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public int getWeekdelay() {
		return weekdelay;
	}

	public void setWeekdelay(int weekdelay) {
		this.weekdelay = weekdelay;
	}

	public Date getNewdate() {
		return newdate;
	}

	public void setNewdate(Date newdate) {
		this.newdate = newdate;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public ProjectStatusLight getRag() {
		return rag;
	}

	public void setRag(ProjectStatusLight rag) {
		this.rag = rag;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectPhase(int id, String summaryphase, Date startdate, Date enddate, int weekdelay, Date newdate,
			int progress, ProjectStatusLight rag, Project project) {
		super();
		this.id = id;
		this.summaryphase = summaryphase;
		this.startdate = startdate;
		this.enddate = enddate;
		this.weekdelay = weekdelay;
		this.newdate = newdate;
		this.progress = progress;
		this.rag = rag;
		this.project = project;
	}
	
	public ProjectPhase() {
		
	}
	
}
