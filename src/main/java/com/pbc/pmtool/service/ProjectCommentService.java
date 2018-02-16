package com.pbc.pmtool.service;

import com.pbc.pmtool.entity.ProjectComment;

public interface ProjectCommentService {

	public ProjectComment findCommentById(int id);
	
	public ProjectComment addProjectComment(ProjectComment projectComment);
	
}
