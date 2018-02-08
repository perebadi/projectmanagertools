package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.repository.ProjectTaskRepository;
import com.pbc.pmtool.service.ProjectTaskService;

@Service("projectTaskServiceImpl")
public class ProjectTaskServiceImpl implements ProjectTaskService {
	
	@Autowired
	@Qualifier("projectTaskRepository")
	private ProjectTaskRepository projectTaskRepository;

	@Override
	public Task addProjectTask(Task task) {
		// TODO Auto-generated method stub
		return projectTaskRepository.save(task);
	}

	@Override
	public Task findProjectTaskById(int id) {
		// TODO Auto-generated method stub
		return projectTaskRepository.findById(id);
	}

	@Override
	public List<Task> listPageableProjectt(int pageno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> listProjectByProject(Project project) {
		return projectTaskRepository.findByProject(project);
	}

	@Override
	public List<Task> listProjectTasks() {
		return projectTaskRepository.findAll();
	}
	
	@Override
	public List<Task> listProjectTasks(int status) {
		return projectTaskRepository.findByStatus(status);
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
	public List<Task> listProjectTasks(Project project, int status) {
		// TODO Auto-generated method stub
		return projectTaskRepository.findByProjectAndStatus(project,status);
	}

}
