package com.pbc.pmtool.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.pbc.pmtool.enums.ProblemImpactEnum;
import com.pbc.pmtool.enums.ProblemResponsableEnum;
import com.pbc.pmtool.enums.ProblemStatusEnum;
import com.pbc.pmtool.enums.ProblemTypeEnum;
import com.pbc.pmtool.enums.RiskProbabilityEnum;
import com.pbc.pmtool.enums.RiskStrategyEnum;

public class FormRiskModel extends FormProblemRiskModel {

	@NotNull
	private RiskProbabilityEnum probability;
	
	@NotNull
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

	public FormRiskModel(String dateproblem, int week, String summaryproblem, String txtproblem,
			ProblemStatusEnum status, ProblemResponsableEnum responsable, ProblemImpactEnum impact,
			ProblemTypeEnum type, String actions, Date dateclose, int idproblem, RiskProbabilityEnum probability,
			RiskStrategyEnum strategy) {
		super(dateproblem, week, summaryproblem, txtproblem, status, responsable, impact, type, actions, dateclose,
				idproblem);
		this.probability = probability;
		this.strategy = strategy;
	}
	
	public FormRiskModel() {
		super();
	}
	
}
