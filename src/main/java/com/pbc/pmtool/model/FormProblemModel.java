package com.pbc.pmtool.model;





public class FormProblemModel {
	
	private String dateproblem;
	private int week;
	private String summaryproblem;
	private String txtproblem;
	
	
	
	



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







	public FormProblemModel(String dateproblem, int week, String summaryproblem, String txtproblem) {
		super();
		this.dateproblem = dateproblem;
		this.week = week;
		this.summaryproblem = summaryproblem;
		this.txtproblem = txtproblem;
	}







	public FormProblemModel() {
		
	}
	

}
