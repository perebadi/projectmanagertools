package com.pbc.pmtool.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.ProjectComment;

@Repository("projectCommentRepository")
public interface ProjectCommentRepository extends JpaRepository<ProjectComment, Serializable> {

	public abstract ProjectComment findById(int id);
}
