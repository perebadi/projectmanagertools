package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectEscalation;
import com.pbc.pmtool.repository.ProjectEscalationRepository;
import com.pbc.pmtool.service.ProjectEscalationService;


@Service("projectEscalationServiceImpl")
public class ProjectEscalationServiceImpl implements ProjectEscalationService {
	
	@Autowired
	@Qualifier("projectEscalationRepository")
	private ProjectEscalationRepository projectEscalationRepository;

	@Override
	public ProjectEscalation addProjectEscalation(ProjectEscalation projectEscalation) {
		// TODO Auto-generated method stub
		return projectEscalationRepository.save(projectEscalation);
	}

	@Override
	public ProjectEscalation findProjectEscalationById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectEscalation> listPageableProjectt(int pageno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectEscalation> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectEscalation> listProjectByProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectEscalation> listProjectEscalations() {
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
