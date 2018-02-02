package com.pbc.pmtool.model;



public class FormRagModel {
	
	private int id;
	private int projectStatus;
	private int projectStatusConfidence;
	private int projectDeliveryConfidence;
	private int projectGovernance;
	private int projectBusinessChange;
	private int projectBenefitsRealisation;
	private int projectDependency;
	private int projectResourcing;
	private int projectScope;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}
	public int getProjectStatusConfidence() {
		return projectStatusConfidence;
	}
	public void setProjectStatusConfidence(int projectStatusConfidence) {
		this.projectStatusConfidence = projectStatusConfidence;
	}
	public int getProjectDeliveryConfidence() {
		return projectDeliveryConfidence;
	}
	public void setProjectDeliveryConfidence(int projectDeliveryConfidence) {
		this.projectDeliveryConfidence = projectDeliveryConfidence;
	}
	public int getProjectGovernance() {
		return projectGovernance;
	}
	public void setProjectGovernance(int projectGovernance) {
		this.projectGovernance = projectGovernance;
	}
	public int getProjectBusinessChange() {
		return projectBusinessChange;
	}
	public void setProjectBusinessChange(int projectBusinessChange) {
		this.projectBusinessChange = projectBusinessChange;
	}
	public int getProjectBenefitsRealisation() {
		return projectBenefitsRealisation;
	}
	public void setProjectBenefitsRealisation(int projectBenefitsRealisation) {
		this.projectBenefitsRealisation = projectBenefitsRealisation;
	}
	public int getProjectDependency() {
		return projectDependency;
	}
	public void setProjectDependency(int projectDependency) {
		this.projectDependency = projectDependency;
	}
	public int getProjectResourcing() {
		return projectResourcing;
	}
	public void setProjectResourcing(int projectResourcing) {
		this.projectResourcing = projectResourcing;
	}
	public int getProjectScope() {
		return projectScope;
	}
	public void setProjectScope(int projectScope) {
		this.projectScope = projectScope;
	}
	
	
	
	public FormRagModel(int id, int projectStatus, int projectStatusConfidence, int projectDeliveryConfidence,
			int projectGovernance, int projectBusinessChange, int projectBenefitsRealisation, int projectDependency,
			int projectResourcing, int projectScope) {
		super();
		this.id = id;
		this.projectStatus = projectStatus;
		this.projectStatusConfidence = projectStatusConfidence;
		this.projectDeliveryConfidence = projectDeliveryConfidence;
		this.projectGovernance = projectGovernance;
		this.projectBusinessChange = projectBusinessChange;
		this.projectBenefitsRealisation = projectBenefitsRealisation;
		this.projectDependency = projectDependency;
		this.projectResourcing = projectResourcing;
		this.projectScope = projectScope;
	}
	public FormRagModel() {
	}
	

}
