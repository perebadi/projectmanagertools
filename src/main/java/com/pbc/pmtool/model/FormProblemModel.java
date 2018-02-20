package com.pbc.pmtool.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.pbc.pmtool.enums.ProblemImpactEnum;
import com.pbc.pmtool.enums.ProblemResponsableEnum;
import com.pbc.pmtool.enums.ProblemStatusEnum;
import com.pbc.pmtool.enums.ProblemTypeEnum;

public class FormProblemModel extends FormProblemRiskModel{
	
	@NotNull
	private Date estimatedclosingdate;

	public Date getEstimatedclosingdate() {
		return estimatedclosingdate;
	}

	public void setEstimatedclosingdate(Date estimatedclosingdate) {
		this.estimatedclosingdate = estimatedclosingdate;
	}
	
	public FormProblemModel(String dateproblem, int week, String summaryproblem, String txtproblem,
			ProblemStatusEnum status, ProblemResponsableEnum responsable, ProblemImpactEnum impact,
			ProblemTypeEnum type, String actions, Date dateclose, int idproblem, Date estimatedclosingdate) {
		super(dateproblem, week, summaryproblem, txtproblem, status, responsable, impact, type, actions, dateclose,
				idproblem);
		this.estimatedclosingdate = estimatedclosingdate;
	}

	public FormProblemModel() {
		super();
	}
	
}
