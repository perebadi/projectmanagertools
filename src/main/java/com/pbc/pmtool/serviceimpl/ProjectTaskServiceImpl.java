package com.pbc.pmtool.serviceimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.Task;
import com.pbc.pmtool.model.ProjectTaskModel;
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
	public List<ProjectTaskModel> countRecordsByProject(String username) {
		List<ProjectTaskModel> projectTastModels  = new ArrayList<ProjectTaskModel>();
		List<Object[]> salida = projectTaskRepository.findProjectTask(username);
		System.out.println("before");

		ProjectTaskModel fModel;
		
		for (Object[] res : salida ){
			fModel = new ProjectTaskModel();

			System.out.println((Integer)res[0]);
			System.out.println((String)res[1]);
			System.out.println((String)res[2]);
			System.out.println((Boolean)res[3]);
			System.out.println((BigInteger)res[4]);
			System.out.println((BigInteger)res[5]);
			System.out.println((BigInteger)res[6]);
			System.out.println((BigInteger)res[7]);
			
			
			
			fModel.setId((Integer)res[0]);
			fModel.setProjectname((String)res[1]);
			fModel.setUsername((String)res[2]);
			fModel.setProjectactive((Boolean)res[3]);
			fModel.setBacklog((BigInteger)res[4]);
			fModel.setTodo((BigInteger)res[5]);
			fModel.setInprogress((BigInteger)res[6]);
			fModel.setDone((BigInteger)res[7]);
			
			
			System.out.println(fModel.getProjectname());
			projectTastModels.add(fModel);
			
		}	
		return projectTastModels;
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
