package com.pbc.pmtool.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.pbc.pmtool.enums.ProblemImpactEnum;
import com.pbc.pmtool.enums.ProblemResponsableEnum;
import com.pbc.pmtool.enums.ProblemStatusEnum;
import com.pbc.pmtool.enums.ProblemTypeEnum;

@Entity
public class Problem extends ProjectProblem {

	@Column(name = "estimatedclosingdate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date estimatedclosingdate;

	public Date getEstimatedclosingdate() {
		return estimatedclosingdate;
	}

	public void setEstimatedclosingdate(Date estimatedclosingdate) {
		this.estimatedclosingdate = estimatedclosingdate;
	}

	public Problem(int id, Date dateproblem, int week, String summaryproblem, String txtproblem,
			ProblemStatusEnum status, ProblemResponsableEnum responsable, ProblemImpactEnum impact,
			ProblemTypeEnum type, String actions, Date dateclose, Project project, Date estimatedclosingdate) {
		super(id, dateproblem, week, summaryproblem, txtproblem, status, responsable, impact, type, actions, dateclose,
				project);
		this.estimatedclosingdate = estimatedclosingdate;
	}
	
	public Problem() {
		super();
	}
	
}
