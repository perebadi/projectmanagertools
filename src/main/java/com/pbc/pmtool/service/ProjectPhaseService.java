package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectPhase;


public interface ProjectPhaseService {
	public abstract ProjectPhase addProjectPhase(ProjectPhase projectPhase);
	public abstract ProjectPhase findProjectPhaseById(int id);
	
	
	//NEW
	public abstract List<ProjectPhase> listPageableProjectt(int pageno);
	public abstract List<ProjectPhase> listPageableProject(int pageno, String search);
	public abstract List<ProjectPhase> listProjectByProject(Project project);
	public abstract List<ProjectPhase> listProjectPhases();
	public abstract int countRecords();
	public abstract void removeSource(int id);
}
