package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.model.ProjectTaskModel;


public interface ProjectTaskService {
	public abstract Task addProjectTask(Task task);
	public abstract Task findProjectTaskById(int id);
	
	
	//NEW
	public abstract List<Task> listPageableProjectt(int pageno);
	public abstract List<Task> listPageableProject(int pageno, String search);
	public abstract List<Task> listProjectByProject(Project project);
	public abstract List<Task> listProjectTasks();
	
	public abstract List<Task> listProjectTasks(int status);
	public abstract List<Task> listProjectTasks(Project project, int status);

	public abstract List<ProjectTaskModel> countRecordsByProject(String username);
	public abstract void removeSource(int id);
}
