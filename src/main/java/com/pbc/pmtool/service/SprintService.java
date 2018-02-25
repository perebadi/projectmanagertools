package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.model.FormSprint;

public interface SprintService {

	public FormSprint add(FormSprint sprintModel, int project);
	
	public List<FormSprint> getByProject(int project);
	
}
