package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectProblem;
import com.pbc.pmtool.repository.ProjectProblemRepository;
import com.pbc.pmtool.service.ProjectProblemService;

@Service("projectProblemServiceImpl")
public class ProjectProblemServiceImpl implements ProjectProblemService {
	
	@Autowired
	@Qualifier("projectProblemRepository")
	private ProjectProblemRepository projectProblemRepository;

	@Override
	public ProjectProblem addProjectProblem(ProjectProblem projectProblem) {
		// TODO Auto-generated method stub
		return projectProblemRepository.save(projectProblem);
	}

	@Override
	public ProjectProblem findProjectProblemById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectProblem> listPageableProjectt(int pageno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectProblem> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectProblem> listProjectByProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectProblem> listProjectProblems() {
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
