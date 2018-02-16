package com.pbc.pmtool.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class FormAchievementModel {
	
	private int idachievement;
	
	@NotEmpty
	private String dateachievement;
	
	@NotNull
	private int week;
	
	@NotEmpty
	private String summaryachievement;
	private String txtachievement;
	
	
	
	
	
	
	
	public int getIdachievement() {
		return idachievement;
	}
	public void setIdachievement(int idachievement) {
		this.idachievement = idachievement;
	}
	public String getDateachievement() {
		return dateachievement;
	}
	public void setDateachievement(String dateachievement) {
		this.dateachievement = dateachievement;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getSummaryachievement() {
		return summaryachievement;
	}
	public void setSummaryachievement(String summaryachievement) {
		this.summaryachievement = summaryachievement;
	}
	public String getTxtachievement() {
		return txtachievement;
	}
	public void setTxtachievement(String txtachievement) {
		this.txtachievement = txtachievement;
	}
	
	
	
	
	public FormAchievementModel(int idachievement, String dateachievement, int week, String summaryachievement,
			String txtachievement) {
		super();
		this.idachievement = idachievement;
		this.dateachievement = dateachievement;
		this.week = week;
		this.summaryachievement = summaryachievement;
		this.txtachievement = txtachievement;
	}
	public FormAchievementModel() {
		
	}
	

}
