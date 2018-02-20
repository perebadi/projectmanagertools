package com.pbc.pmtool.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.pbc.pmtool.enums.ProblemImpactEnum;
import com.pbc.pmtool.enums.ProblemResponsableEnum;
import com.pbc.pmtool.enums.ProblemStatusEnum;
import com.pbc.pmtool.enums.ProblemTypeEnum;
import com.pbc.pmtool.enums.RiskProbabilityEnum;
import com.pbc.pmtool.enums.RiskStrategyEnum;

@Entity
public class Risk extends ProjectProblem {

	@Enumerated(EnumType.STRING)
	@Column(name="probability", length=6)
	private RiskProbabilityEnum probability;
	
	@Enumerated(EnumType.STRING)
	@Column(name="strategy", length=8)
	private RiskStrategyEnum strategy;

	public RiskProbabilityEnum getProbability() {
		return probability;
	}

	public void setProbability(RiskProbabilityEnum probability) {
		this.probability = probability;
	}

	public RiskStrategyEnum getStrategy() {
		return strategy;
	}

	public void setStrategy(RiskStrategyEnum strategy) {
		this.strategy = strategy;
	}

	public Risk(int id, Date dateproblem, int week, String summaryproblem, String txtproblem, ProblemStatusEnum status,
			ProblemResponsableEnum responsable, ProblemImpactEnum impact, ProblemTypeEnum type, String actions,
			Date dateclose, Project project, RiskProbabilityEnum probability, RiskStrategyEnum strategy) {
		super(id, dateproblem, week, summaryproblem, txtproblem, status, responsable, impact, type, actions, dateclose,
				project);
		this.probability = probability;
		this.strategy = strategy;
	}
	
	public Risk() {
		super();
	}
	
}
