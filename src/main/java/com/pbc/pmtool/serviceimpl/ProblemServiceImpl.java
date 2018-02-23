package com.pbc.pmtool.serviceimpl;

import java.text.SimpleDateFormat;

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
		FormProblemModel problemModel = problemMapper.toModel(problemRepository.findByIdAndProject_id(id, project));
		
		if(problemModel != null) {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	        String date = DATE_FORMAT.format(problemModel.getEstimatedclosingdate());
			
			problemModel.setEstimatedclosingdatestr(date);
			
			date = DATE_FORMAT.format(problemModel.getDateproblem());
			
			problemModel.setDateproblemstr(date);
			
			try {
				date = DATE_FORMAT.format(problemModel.getDateclose());
			
				problemModel.setDateclosestr(date);
			}catch (NullPointerException e) {
				problemModel.setDateclosestr("");
			}
			
			return problemModel;
		}else {
			return null;
		}
	}
}
