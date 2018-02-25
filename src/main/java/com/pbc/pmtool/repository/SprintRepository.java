package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Sprint;

@Repository("sprintRepository")
public interface SprintRepository extends JpaRepository<Sprint, Serializable> {

	public List<Sprint> findByProject_id(int project);
	public Sprint findById(int id);
}
