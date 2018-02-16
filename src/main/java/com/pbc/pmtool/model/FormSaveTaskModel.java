package com.pbc.pmtool.model;

import javax.validation.constraints.NotNull;

public class FormSaveTaskModel {

	@NotNull
	private int taskId;
	@NotNull
	private int time;
	@NotNull
	private int unit;

	public FormSaveTaskModel() {}
	
	public FormSaveTaskModel(int taskId, int time, int unit) {
		super();
		this.taskId = taskId;
		this.time = time;
		this.unit = unit;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

}
