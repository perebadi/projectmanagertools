package com.pbc.pmtool.model;

import javax.validation.constraints.NotNull;

public class FormFinancialModel {
	
	@NotNull
	private Double TVC;
	
	@NotNull
	private Double TIC;
	
	@NotNull
	private Double OP;
	
	@NotNull
	private Double budgettodate;
	
	@NotNull
	private Double costestimated;
	
	@NotNull
	private Double EACOP;
	
	@NotNull
	private Double variance;
	
	@NotNull
	private Double certifiedprogress;
	
	@NotNull
	private Double invoiced;
	
	
	
	
	
	public Double getTVC() {
		return TVC;
	}





	public void setTVC(Double tVC) {
		TVC = tVC;
	}





	public Double getTIC() {
		return TIC;
	}





	public void setTIC(Double tIC) {
		TIC = tIC;
	}





	public Double getOP() {
		return OP;
	}





	public void setOP(Double oP) {
		OP = oP;
	}





	public Double getBudgettodate() {
		return budgettodate;
	}





	public void setBudgettodate(Double budgettodate) {
		this.budgettodate = budgettodate;
	}





	public Double getCostestimated() {
		return costestimated;
	}





	public void setCostestimated(Double costestimated) {
		this.costestimated = costestimated;
	}





	public Double getEACOP() {
		return EACOP;
	}





	public void setEACOP(Double eACOP) {
		EACOP = eACOP;
	}





	public Double getVariance() {
		return variance;
	}





	public void setVariance(Double variance) {
		this.variance = variance;
	}





	public Double getCertifiedprogress() {
		return certifiedprogress;
	}





	public void setCertifiedprogress(Double certifiedprogress) {
		this.certifiedprogress = certifiedprogress;
	}





	public Double getInvoiced() {
		return invoiced;
	}





	public void setInvoiced(Double invoiced) {
		this.invoiced = invoiced;
	}





	public FormFinancialModel(Double tVC, Double tIC, Double oP, Double budgettodate, Double costestimated,
			Double eACOP, Double variance, Double certifiedprogress, Double invoiced) {
		super();
		TVC = tVC;
		TIC = tIC;
		OP = oP;
		this.budgettodate = budgettodate;
		this.costestimated = costestimated;
		EACOP = eACOP;
		this.variance = variance;
		this.certifiedprogress = certifiedprogress;
		this.invoiced = invoiced;
	}





	public FormFinancialModel() {
		
	}
	

}
