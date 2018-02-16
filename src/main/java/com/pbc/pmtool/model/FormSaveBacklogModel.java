package com.pbc.pmtool.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class FormSaveBacklogModel {

	private String comment;
	@NotNull
	private int taskId;
	@NotEmpty
	private String username;

	public FormSaveBacklogModel() {
	}

	public FormSaveBacklogModel(String comment, int taskId, String username) {
		super();
		this.comment = comment;
		this.taskId = taskId;
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
