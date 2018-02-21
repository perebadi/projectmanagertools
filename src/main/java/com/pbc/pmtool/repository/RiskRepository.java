package com.pbc.pmtool.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Risk;

@Repository("riskRepository")
public interface RiskRepository extends JpaRepository<Risk, Serializable> {

	public Risk findByIdAndProject_id(int id, int project);
}
