package com.pbc.pmtool.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.ProjectComment;
import com.pbc.pmtool.repository.ProjectCommentRepository;
import com.pbc.pmtool.service.ProjectCommentService;

@Service("projectCommentServiceImpl")
public class ProjectCommentServiceImpl implements ProjectCommentService {

	@Autowired
	@Qualifier("projectCommentRepository")
	private ProjectCommentRepository projectCommentRepository;
	
	@Override
	public ProjectComment findCommentById(int id) {
		return projectCommentRepository.findById(id);
	}

	@Override
	public ProjectComment addProjectComment(ProjectComment projectComment) {
		return projectCommentRepository.save(projectComment);
	}
}
