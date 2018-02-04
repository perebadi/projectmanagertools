package com.pbc.pmtool.model;





public class FormNextStepModel {
	
	private int idnextstep;
	private String datenextstep;
	private int week;
	private String summarynextstep;
	private String txtnextstep;
	
	
	
	
	public int getIdnextstep() {
		return idnextstep;
	}




	public void setIdnextstep(int idnextstep) {
		this.idnextstep = idnextstep;
	}




	public String getDatenextstep() {
		return datenextstep;
	}




	public void setDatenextstep(String datenextstep) {
		this.datenextstep = datenextstep;
	}




	public int getWeek() {
		return week;
	}




	public void setWeek(int week) {
		this.week = week;
	}




	public String getSummarynextstep() {
		return summarynextstep;
	}




	public void setSummarynextstep(String summarynextstep) {
		this.summarynextstep = summarynextstep;
	}




	public String getTxtnextstep() {
		return txtnextstep;
	}




	public void setTxtnextstep(String txtnextstep) {
		this.txtnextstep = txtnextstep;
	}




	



	public FormNextStepModel(int idnextstep, String datenextstep, int week, String summarynextstep,
			String txtnextstep) {
		super();
		this.idnextstep = idnextstep;
		this.datenextstep = datenextstep;
		this.week = week;
		this.summarynextstep = summarynextstep;
		this.txtnextstep = txtnextstep;
	}

	public FormNextStepModel() {
		
	}
	

}
