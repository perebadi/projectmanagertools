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

	private String estimatedclosingdatestr;
	
	public String getEstimatedclosingdatestr() {
		return estimatedclosingdatestr;
	}

	public void setEstimatedclosingdatestr(String estimatedclosingdatestr) {
		this.estimatedclosingdatestr = estimatedclosingdatestr;
	}

	public Date getEstimatedclosingdate() {
		return estimatedclosingdate;
	}

	public void setEstimatedclosingdate(Date estimatedclosingdate) {
		this.estimatedclosingdate = estimatedclosingdate;
	}

	public FormProblemModel(Date dateproblem, String dateproblemstr, int week, String summaryproblem, String txtproblem,
			ProblemStatusEnum status, ProblemResponsableEnum responsable, ProblemImpactEnum impact,
			ProblemTypeEnum type, String actions, Date dateclose, String dateclosestr, int idproblem,
			Date estimatedclosingdate, String estimatedclosingdatestr) {
		super(dateproblem, dateproblemstr, week, summaryproblem, txtproblem, status, responsable, impact, type, actions,
				dateclose, dateclosestr, idproblem);
		this.estimatedclosingdate = estimatedclosingdate;
		this.estimatedclosingdatestr = estimatedclosingdatestr;
	}

	public FormProblemModel() {
		super();
	}
	
}
