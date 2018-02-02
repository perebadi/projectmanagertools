package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectEscalation;


public interface ProjectEscalationService {
	public abstract ProjectEscalation addProjectEscalation(ProjectEscalation projectEscalation);
	public abstract ProjectEscalation findProjectEscalationById(int id);
	
	
	//NEW
	public abstract List<ProjectEscalation> listPageableProjectt(int pageno);
	public abstract List<ProjectEscalation> listPageableProject(int pageno, String search);
	public abstract List<ProjectEscalation> listProjectByProject(Project project);
	public abstract List<ProjectEscalation> listProjectEscalations();
	public abstract int countRecords();
	public abstract void removeSource(int id);
}
