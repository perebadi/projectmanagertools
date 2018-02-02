package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectStatus;


public interface ProjectStatusService {
	public abstract ProjectStatus addProjectStatus(ProjectStatus projectStatus);
	public abstract ProjectStatus findProjectStatusById(int id);
	
	
	//NEW
	public abstract List<ProjectStatus> listPageableProjectt(int pageno);
	public abstract List<ProjectStatus> listPageableProject(int pageno, String search);
	public abstract List<ProjectStatus> listProjectByProject(Project project);
	public abstract List<ProjectStatus> listProjectStatuses();
	public abstract int countRecords();
	public abstract void removeSource(int id);
}
