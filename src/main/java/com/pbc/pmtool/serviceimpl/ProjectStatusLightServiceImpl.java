package com.pbc.pmtool.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.ProjectStatusLight;
import com.pbc.pmtool.repository.ProjectStatusLightRepository;
import com.pbc.pmtool.service.ProjectStatusLightService;

@Service("projectStatusLightServiceImpl")
public class ProjectStatusLightServiceImpl implements ProjectStatusLightService {
	
	@Autowired
	@Qualifier("projectStatusLightRepository")
	private ProjectStatusLightRepository projectStatusLightRepository;

	@Override
	public ProjectStatusLight addProjectStatusLight(ProjectStatusLight projectStatusLight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectStatusLight findProjectStatusLightById(int id) {
		return projectStatusLightRepository.findById(id);
	}

	@Override
	public List<ProjectStatusLight> listPageableProjectt(int pageno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectStatusLight> listPageableProject(int pageno, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectStatusLight> listProjectByProject(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectStatusLight> listProjectStatusLights() {
		// TODO Auto-generated method stub
		return projectStatusLightRepository.findAll();
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
	public ProjectStatusLight findProjectStatusLightByStatusname(String statusname) {
		return projectStatusLightRepository.findByStatusname(statusname);
		
	}

}
