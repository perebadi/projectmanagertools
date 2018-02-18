package com.pbc.pmtool.entity;

import java.io.Serializable;
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
@Table(name = "comment")
public class Comment implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "detail", columnDefinition = "TEXT")
	private String detail;
	
	@NotNull
	@Column(name = "dateacomment")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date datecomment;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Task task;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getDatecomment() {
		return datecomment;
	}

	public void setDatecomment(Date datecomment) {
		this.datecomment = datecomment;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Comment(int id, String detail, Date datecomment, Task task) {
		super();
		this.id = id;
		this.detail = detail;
		this.datecomment = datecomment;
		this.task = task;
	}
	
	public Comment() {
		
	}
	

}
