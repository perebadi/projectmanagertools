package com.pbc.pmtool.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.model.FormProblemModel;
import com.pbc.pmtool.repository.ProblemRepository;
import com.pbc.pmtool.service.ProblemService;
import com.pbc.pmtool.service.mapper.ProblemMapper;

@Service("problemServiceImpl")
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	@Qualifier("problemRepository")
	private ProblemRepository problemRepository;
	
	@Autowired
	private ProblemMapper problemMapper;
	
	@Override
	public FormProblemModel getProblem(int id, int project) {
		return problemMapper.toModel(problemRepository.findByIdAndProject_id(id, project));
	}

}
