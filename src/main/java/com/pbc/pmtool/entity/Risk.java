package com.pbc.pmtool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.pbc.pmtool.enums.RiskProbabilityEnum;
import com.pbc.pmtool.enums.RiskStrategyEnum;

@Entity
public class Risk extends ProjectProblem {

	@Enumerated(EnumType.STRING)
	@Column(name="probability")
	private RiskProbabilityEnum probability;
	
	@Enumerated(EnumType.STRING)
	@Column(name="strategy")
	private RiskStrategyEnum strategy;
	
}
