package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectProblem;


public interface ProjectProblemService {
	public abstract ProjectProblem addProjectProblem(ProjectProblem projectProblem);
	public abstract ProjectProblem findProjectProblemById(int id);
	
	
	//NEW
	public abstract List<ProjectProblem> listPageableProjectt(int pageno);
	public abstract List<ProjectProblem> listPageableProject(int pageno, String search);
	public abstract List<ProjectProblem> listProjectByProject(Project project);
	public abstract List<ProjectProblem> listProjectProblems();
	public abstract int countRecords();
	public abstract void removeSource(int id);
}
