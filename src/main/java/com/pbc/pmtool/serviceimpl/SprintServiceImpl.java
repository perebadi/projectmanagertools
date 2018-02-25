package com.pbc.pmtool.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.Sprint;
import com.pbc.pmtool.model.FormSprint;
import com.pbc.pmtool.repository.SprintRepository;
import com.pbc.pmtool.service.ProjectService;
import com.pbc.pmtool.service.SprintService;
import com.pbc.pmtool.service.mapper.SprintMapper;

@Service("sprintServiceImpl")
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SprintMapper sprintMapper;
	
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectServiceImpl;
	
	@Autowired
	@Qualifier("sprintRepository")
	private SprintRepository sprintRepository;
	
	@Override
	public FormSprint add(FormSprint sprintModel, int project) {
		Sprint sprintEntity = sprintMapper.toEntity(sprintModel);
		
		sprintEntity.setProject(projectServiceImpl.findProjectById(project));
		
		return sprintMapper.toModel(sprintRepository.save(sprintEntity));
	}

	@Override
	public List<FormSprint> getByProject(int project) {
		List<FormSprint> sprintsModel = new ArrayList<FormSprint>();
		
		for(Sprint sprintEntity : sprintRepository.findByProject_id(project)) {
			sprintsModel.add(sprintMapper.toModel(sprintEntity));
		}
		
		return sprintsModel;
	}
	
}
