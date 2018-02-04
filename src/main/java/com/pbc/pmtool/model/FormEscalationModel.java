package com.pbc.pmtool.model;

public class FormEscalationModel {

	
	private int idescalation;
	private String dateescalation;
	private int week;
	private String summaryescalation;
	private String txtescalation;
	
	
	
	
	

	public int getIdescalation() {
		return idescalation;
	}

	public void setIdescalation(int idescalation) {
		this.idescalation = idescalation;
	}

	public String getDateescalation() {
		return dateescalation;
	}

	public void setDateescalation(String dateescalation) {
		this.dateescalation = dateescalation;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public String getSummaryescalation() {
		return summaryescalation;
	}

	public void setSummaryescalation(String summaryescalation) {
		this.summaryescalation = summaryescalation;
	}

	public String getTxtescalation() {
		return txtescalation;
	}

	public void setTxtescalation(String txtescalation) {
		this.txtescalation = txtescalation;
	}

	
	
	

	public FormEscalationModel(int idescalation, String dateescalation, int week, String summaryescalation,
			String txtescalation) {
		super();
		this.idescalation = idescalation;
		this.dateescalation = dateescalation;
		this.week = week;
		this.summaryescalation = summaryescalation;
		this.txtescalation = txtescalation;
	}

	public FormEscalationModel() {

	}

}
