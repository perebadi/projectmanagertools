package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.User;


public interface ProjectService {
	public abstract Project addProject(Project project);
	public abstract Project findProjectById(int id);
	
	
	//NEW
	public abstract List<Project> listPageableProjects(int pageno, User user);
	public abstract List<Project> listPageableProject(int pageno, String search);
	public abstract List<Project> listProjectByUser(User user);
	public abstract List<Project> listProjects();
	public abstract int countRecords(User user);
	public abstract void removeSource(int id);
	
	public abstract Project updateProject(Project project);
}