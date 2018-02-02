package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectStatusLight;


public interface ProjectStatusLightService {
	public abstract ProjectStatusLight addProjectStatusLight(ProjectStatusLight projectStatusLight);
	public abstract ProjectStatusLight findProjectStatusLightById(int id);
	
	
	//NEW
	public abstract List<ProjectStatusLight> listPageableProjectt(int pageno);
	public abstract List<ProjectStatusLight> listPageableProject(int pageno, String search);
	public abstract List<ProjectStatusLight> listProjectByProject(Project project);
	public abstract List<ProjectStatusLight> listProjectStatusLights();
	public abstract ProjectStatusLight findProjectStatusLightByStatusname(String statusname);

	
	public abstract int countRecords();
	public abstract void removeSource(int id);
}
