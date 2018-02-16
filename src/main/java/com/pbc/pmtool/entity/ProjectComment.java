package com.pbc.pmtool.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.Gson;

/**
 * Entidad comentarios en proyectos
 * 
 * @author JEFE DE PROYECTO PM3
 *
 */

@Entity
@Table(name = "projectcomment")
public class ProjectComment implements Comparable<ProjectComment> {

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

	@ElementCollection
	@CollectionTable(name="projectcommenttag", joinColumns= {@JoinColumn(name="id")})
	@Column(name="tag")
	private List<String> tags;
	
	public ProjectComment() {}
	
	public ProjectComment(int id, String comment, User pm, Project project, Date createdOn, Date modifiedOn, List<String> tags) {
		super();
		this.id = id;
		this.comment = comment;
		this.pm = pm;
		this.project = project;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.tags = tags;
	}
	
	public String getTagsJSON() {
		Gson gson = new Gson();
		
		return gson.toJson(getTags());
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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

	@Override
	public int compareTo(ProjectComment o) {
		if(this.getCreatedOn().before(o.getCreatedOn())) {
			return 1;
		}else if(this.getCreatedOn().after(o.getCreatedOn())) {
			return -1;
		}else {
			if(this.getId() < o.getId()) {
				return 1;
			}else {
				return -1;
			}
		}
	}

}