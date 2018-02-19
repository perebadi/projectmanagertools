package com.pbc.pmtool.model;

public class FormNewProjectModel {

	private String projectname;
	private String objectives;
	private Double TVC;
	private Double TIC;
	private Double OP;
	private Double budgettodate;
	private Double costestimated;
	private Double EACOP;
	private Double variance;
	private Double certifiedprogress;
	private Double invoiced;
	private String start;
	private String end;
	private String wbs;
	private int customer_id;
	
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getProjectname() {
		return projectname;
	}

	public String getWbs() {
		return wbs;
	}

	public void setWbs(String wbs) {
		this.wbs = wbs;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public FormNewProjectModel(String projectname, String objectives, Double tVC, Double tIC, Double oP,
			Double budgettodate, Double costestimated, Double eACOP, Double variance, Double certifiedprogress,
			Double invoiced, String start, String end, String wbs) {
		super();
		this.projectname = projectname;
		this.objectives = objectives;
		TVC = tVC;
		TIC = tIC;
		OP = oP;
		this.budgettodate = budgettodate;
		this.costestimated = costestimated;
		EACOP = eACOP;
		this.variance = variance;
		this.certifiedprogress = certifiedprogress;
		this.invoiced = invoiced;
		this.start = start;
		this.end = end;
		this.wbs = wbs;
	}

	public FormNewProjectModel() {

	}

}
