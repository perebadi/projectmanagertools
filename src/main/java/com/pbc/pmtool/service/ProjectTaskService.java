package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.entity.Task;


public interface ProjectTaskService {
	public abstract Task addProjectTask(Task task);
	public abstract Task findProjectTaskById(int id);
	
	
	//NEW
	public abstract List<Task> listPageableProjectt(int pageno);
	public abstract List<Task> listPageableProject(int pageno, String search);
	public abstract List<Task> listProjectByProject(Project project);
	public abstract List<Task> listProjectTasks();
	
	public abstract List<Task> listProjectTasks(int status);

	public abstract int countRecords();
	public abstract void removeSource(int id);
}
