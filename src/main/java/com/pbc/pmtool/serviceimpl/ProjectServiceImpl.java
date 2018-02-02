package com.pbc.pmtool.serviceimpl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectAchievement;
import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.repository.ProjectRepository;
import com.pbc.pmtool.service.ProjectService;

@Service("projectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

	
	@Autowired
	@Qualifier("projectRepository")
	private ProjectRepository projectRepository;
	
	@Override
	public Project addProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Project findProjectById(int id) {
		
		return projectRepository.findById(id);
	}


	@Override
	public List<Project> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> listProjectByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void removeSource(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Project> listPageableProjects(int pageno, User user) {
		Page<Project> pageprojects = projectRepository.findByUser(user, new PageRequest(pageno, 6, Sort.Direction.DESC, "id"));
		List<Project> projects = pageprojects.getContent();
		return projects;
	}

	@Override
	public List<Project> listProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countRecords(User user) {
		List<Project> projects = projectRepository.findByUser(user);
		return projects.size();
	}

	@Override
	public Project updateProject(Project project) {
		// TODO Auto-generated method stub
		System.out.print("CUANTOS"+project.getAchievements().size());
		
		Iterator iterator = project.getAchievements().iterator();
		while(iterator.hasNext()){
		  ProjectAchievement element = (ProjectAchievement) iterator.next();
		  System.out.print("DENTRO" + element.getSummaryachievement());
		}
		
		return projectRepository.save(project); 
	}

}