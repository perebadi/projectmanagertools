package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;


public interface ProjectAchievementService {
	public abstract ProjectAchievement addProjectAchievement(ProjectAchievement projectAchievement);
	public abstract ProjectAchievement findProjectachievementById(int id);
	
	
	//NEW
	public abstract List<ProjectAchievement> listPageableProjectt(int pageno);
	public abstract List<ProjectAchievement> listPageableProject(int pageno, String search);
	public abstract List<ProjectAchievement> listProjectByProject(Project project);
	public abstract List<ProjectAchievement> listProjectAchievements();
	public abstract int countRecords();
	public abstract void removeSource(int id);
	
	public abstract List<ProjectAchievement> listTop5ProjectAchievements(Project project);

}
