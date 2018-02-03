package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectPhase;
import com.pbc.pmtool.repository.ProjectPhaseRepository;
import com.pbc.pmtool.service.ProjectPhaseService;

@Service("projectPhaseServiceImpl")
public class ProjectPhaseServiceImpl implements ProjectPhaseService {
	
	
	@Autowired
	@Qualifier("projectPhaseRepository")
	private ProjectPhaseRepository projectPhaseRepository;

	@Override
	public ProjectPhase addProjectPhase(ProjectPhase projectPhase) {
		
		return projectPhaseRepository.save(projectPhase);
	}

	@Override
	public ProjectPhase findProjectPhaseById(int id) {
		
		return projectPhaseRepository.findById(id);
	}

	@Override
	public List<ProjectPhase> listPageableProjectt(int pageno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectPhase> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectPhase> listProjectByProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectPhase> listProjectPhases() {
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
