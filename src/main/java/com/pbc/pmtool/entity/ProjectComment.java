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

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entidad comentarios en proyectos
 * 
 * @author JEFE DE PROYECTO PM3
 *
 */

@Entity
@Table(name = "projectcomment")
public class ProjectComment {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "comment", nullable = false, length = 255)
	private String comment;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User pm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;
	
	@Column(name = "createdon" , nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdOn;
	
	@Column(name = "modifiedon", nullable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modifiedOn;

	public ProjectComment() {}
	
	public ProjectComment(int id, String comment, User pm, Project project, Date createdOn, Date modifiedOn) {
		super();
		this.id = id;
		this.comment = comment;
		this.pm = pm;
		this.project = project;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getPm() {
		return pm;
	}

	public void setPm(User pm) {
		this.pm = pm;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}
