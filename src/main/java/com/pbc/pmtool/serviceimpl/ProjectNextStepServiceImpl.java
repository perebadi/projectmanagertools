package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectNextStep;
import com.pbc.pmtool.repository.ProjectNextStepRepository;
import com.pbc.pmtool.service.ProjectNextStepService;

@Service("projectNextStepServiceImpl")
public class ProjectNextStepServiceImpl implements ProjectNextStepService {
	
	@Autowired
	@Qualifier("projectNextStepRepository")
	private ProjectNextStepRepository projectNextStepRepository;

	@Override
	public ProjectNextStep addProjectNextStep(ProjectNextStep projectNextStep) {
		// TODO Auto-generated method stub
		return projectNextStepRepository.save(projectNextStep);
	}

	@Override
	public ProjectNextStep findProjectNextStepById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectNextStep> listPageableProjectt(int pageno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectNextStep> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectNextStep> listProjectByProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectNextStep> listProjectNextSteps() {
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

}
