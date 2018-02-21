package com.pbc.pmtool.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Problem;

@Repository("problemRepository")
public interface ProblemRepository extends JpaRepository<Problem, Serializable> {

	public Problem findByIdAndProject_id(int id, int project);
}
