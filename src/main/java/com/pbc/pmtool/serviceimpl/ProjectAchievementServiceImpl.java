package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.repository.ProjectAchievementRepository;
import com.pbc.pmtool.repository.ProjectRepository;
import com.pbc.pmtool.service.ProjectAchievementService;

@Service("projectAchievementServiceImpl")
public class ProjectAchievementServiceImpl implements ProjectAchievementService {
	
	
	@Autowired
	@Qualifier("projectAchievementRepository")
	private ProjectAchievementRepository projectAchievementRepository;

	@Override
	public ProjectAchievement addProjectAchievement(ProjectAchievement projectAchievement) {
		// TODO Auto-generated method stub
		return projectAchievementRepository.save(projectAchievement);
	}

	@Override
	public ProjectAchievement findProjectachievementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectAchievement> listPageableProjectt(int pageno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectAchievement> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectAchievement> listProjectByProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectAchievement> listProjectAchievements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countRecords() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeSource(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProjectAchievement> listTop5ProjectAchievements(Project project ) {
		System.out.println("*************************************");
		System.out.println(project.getProjectname());
		Page<ProjectAchievement> pageachievements = projectAchievementRepository.findByProject(project, new PageRequest(0, 5));
		List<ProjectAchievement> achievements = pageachievements.getContent();
		System.out.println("SIZE  : " +  achievements.size());
		
		return achievements;
	}

}
