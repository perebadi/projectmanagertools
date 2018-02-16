package com.pbc.pmtool.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class FormProblemModel {
	
	@NotEmpty
	private String dateproblem;
	
	@NotNull
	private int week;
	
	@NotEmpty
	private String summaryproblem;
	
	private String txtproblem;
	private int idproblem;
	
	
	



	public int getIdproblem() {
		return idproblem;
	}







	public void setIdproblem(int idproblem) {
		this.idproblem = idproblem;
	}







	public String getDateproblem() {
		return dateproblem;
	}







	public void setDateproblem(String dateproblem) {
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







	public FormProblemModel(String dateproblem, int week, String summaryproblem, String txtproblem, int idproblem) {
		super();
		this.dateproblem = dateproblem;
		this.week = week;
		this.summaryproblem = summaryproblem;
		this.txtproblem = txtproblem;
		this.idproblem = idproblem;
	}







	public FormProblemModel() {
		
	}
	

}
