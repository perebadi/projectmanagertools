package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectNextStep;


public interface ProjectNextStepService {
	public abstract ProjectNextStep addProjectNextStep(ProjectNextStep projectNextStep);
	public abstract ProjectNextStep findProjectNextStepById(int id);
	
	
	//NEW
	public abstract List<ProjectNextStep> listPageableProjectt(int pageno);
	public abstract List<ProjectNextStep> listPageableProject(int pageno, String search);
	public abstract List<ProjectNextStep> listProjectByProject(Project project);
	public abstract List<ProjectNextStep> listProjectNextSteps();
	public abstract int countRecords();
	public abstract void removeSource(int id);
}
