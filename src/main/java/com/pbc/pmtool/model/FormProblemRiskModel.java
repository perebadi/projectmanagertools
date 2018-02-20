package com.pbc.pmtool.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.pbc.pmtool.enums.ProblemImpactEnum;
import com.pbc.pmtool.enums.ProblemResponsableEnum;
import com.pbc.pmtool.enums.ProblemStatusEnum;
import com.pbc.pmtool.enums.ProblemTypeEnum;

public class FormProblemRiskModel {

	@NotEmpty
	private Date dateproblem;
	
	private String dateproblemstr;
	
	@NotNull
	private int week;
	
	@NotEmpty
	private String summaryproblem;
	
	@NotEmpty
	private String txtproblem;
	
	@NotEmpty
	private ProblemStatusEnum status;
	
	@NotEmpty
	private ProblemResponsableEnum responsable;
	
	@NotEmpty
	private ProblemImpactEnum impact;
	
	@NotEmpty
	private ProblemTypeEnum type;
	
	@NotEmpty
	private String actions;
	
	private Date dateclose;
	
	private String dateclosestr;
	
	private int idproblem;
	
	public String getDateclosestr() {
		return dateclosestr;
	}

	public void setDateclosestr(String dateclosestr) {
		this.dateclosestr = dateclosestr;
	}

	public String getDateproblemstr() {
		return dateproblemstr;
	}

	public void setDateproblemstr(String dateproblemstr) {
		this.dateproblemstr = dateproblemstr;
	}

	public Date getDateproblem() {
		return dateproblem;
	}

	public void setDateproblem(Date dateproblem) {
		this.dateproblem = dateproblem;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getSummaryproblem() {
		return summaryproblem;
	}

	public void setSummaryproblem(String summaryproblem) {
		this.summaryproblem = summaryproblem;
	}

	public String getTxtproblem() {
		return txtproblem;
	}

	public void setTxtproblem(String txtproblem) {
		this.txtproblem = txtproblem;
	}

	public ProblemStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProblemStatusEnum status) {
		this.status = status;
	}

	public ProblemResponsableEnum getResponsable() {
		return responsable;
	}

	public void setResponsable(ProblemResponsableEnum responsable) {
		this.responsable = responsable;
	}

	public ProblemImpactEnum getImpact() {
		return impact;
	}

	public void setImpact(ProblemImpactEnum impact) {
		this.impact = impact;
	}

	public ProblemTypeEnum getType() {
		return type;
	}

	public void setType(ProblemTypeEnum type) {
		this.type = type;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public Date getDateclose() {
		return dateclose;
	}

	public void setDateclose(Date dateclose) {
		this.dateclose = dateclose;
	}

	public int getIdproblem() {
		return idproblem;
	}

	public void setIdproblem(int idproblem) {
		this.idproblem = idproblem;
	}

	public FormProblemRiskModel(Date dateproblem, String dateproblemstr, int week, String summaryproblem,
			String txtproblem, ProblemStatusEnum status, ProblemResponsableEnum responsable, ProblemImpactEnum impact,
			ProblemTypeEnum type, String actions, Date dateclose, String dateclosestr, int idproblem) {
		super();
		this.dateproblem = dateproblem;
		this.dateproblemstr = dateproblemstr;
		this.week = week;
		this.summaryproblem = summaryproblem;
		this.txtproblem = txtproblem;
		this.status = status;
		this.responsable = responsable;
		this.impact = impact;
		this.type = type;
		this.actions = actions;
		this.dateclose = dateclose;
		this.dateclosestr = dateclosestr;
		this.idproblem = idproblem;
	}

	public FormProblemRiskModel() {}
	
}
