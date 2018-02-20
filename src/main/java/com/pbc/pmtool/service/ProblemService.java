package com.pbc.pmtool.service;

import com.pbc.pmtool.model.FormProblemModel;

public interface ProblemService {

	public FormProblemModel getProblem(int id, int project);
	
}
