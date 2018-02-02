package com.pbc.pmtool.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "projectname")
	private String projectname;

	@Column(name = "objectives")
	private String objectives;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectStatus;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectStatusConfidence;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectDeliveryConfidence;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectGovernance;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectBusinessChange;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectBenefitsRealisation;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectDependency;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectResourcing;

	@OneToOne(fetch = FetchType.LAZY)
	private ProjectStatusLight projectScope;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<ProjectAchievement> achievements = new HashSet<ProjectAchievement>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<ProjectEscalation> escalations = new HashSet<ProjectEscalation>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<ProjectNextStep> nextsteps = new HashSet<ProjectNextStep>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<ProjectProblem> problems = new HashSet<ProjectProblem>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<ProjectPhase> phases = new HashSet<ProjectPhase>();

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@Column(name = "TVC")
	private Double TVC;

	@Column(name = "TIC")
	private Double TIC;

	@Column(name = "OP")
	private Double OP;

	@Column(name = "budgettodate")
	private Double budgettodate;

	@Column(name = "costestimated")
	private Double costestimated;

	@Column(name = "EACOP")
	private Double EACOP;

	@Column(name = "variance")
	private Double variance;

	@Column(name = "certifiedprogress")
	private Double certifiedprogress;

	@Column(name = "invoiced")
	private Double invoiced;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectname() {
		return projectname;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ProjectStatusLight getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatusLight projectStatus) {
		this.projectStatus = projectStatus;
	}

	public ProjectStatusLight getProjectStatusConfidence() {
		return projectStatusConfidence;
	}

	public void setProjectStatusConfidence(ProjectStatusLight projectStatusConfidence) {
		this.projectStatusConfidence = projectStatusConfidence;
	}

	public ProjectStatusLight getProjectDeliveryConfidence() {
		return projectDeliveryConfidence;
	}

	public void setProjectDeliveryConfidence(ProjectStatusLight projectDeliveryConfidence) {
		this.projectDeliveryConfidence = projectDeliveryConfidence;
	}

	public ProjectStatusLight getProjectGovernance() {
		return projectGovernance;
	}

	public void setProjectGovernance(ProjectStatusLight projectGovernance) {
		this.projectGovernance = projectGovernance;
	}

	public ProjectStatusLight getProjectBusinessChange() {
		return projectBusinessChange;
	}

	public void setProjectBusinessChange(ProjectStatusLight projectBusinessChange) {
		this.projectBusinessChange = projectBusinessChange;
	}

	public ProjectStatusLight getProjectBenefitsRealisation() {
		return projectBenefitsRealisation;
	}

	public void setProjectBenefitsRealisation(ProjectStatusLight projectBenefitsRealisation) {
		this.projectBenefitsRealisation = projectBenefitsRealisation;
	}

	public ProjectStatusLight getProjectDependency() {
		return projectDependency;
	}

	public void setProjectDependency(ProjectStatusLight projectDependency) {
		this.projectDependency = projectDependency;
	}

	public ProjectStatusLight getProjectResourcing() {
		return projectResourcing;
	}

	public void setProjectResourcing(ProjectStatusLight projectResourcing) {
		this.projectResourcing = projectResourcing;
	}

	public ProjectStatusLight getProjectScope() {
		return projectScope;
	}

	public void setProjectScope(ProjectStatusLight projectScope) {
		this.projectScope = projectScope;
	}

	public Set<ProjectAchievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(Set<ProjectAchievement> achievements) {
		this.achievements = achievements;
	}

	public Set<ProjectEscalation> getEscalations() {
		return escalations;
	}

	public void setEscalations(Set<ProjectEscalation> escalations) {
		this.escalations = escalations;
	}

	public Set<ProjectNextStep> getNextsteps() {
		return nextsteps;
	}

	public void setNextsteps(Set<ProjectNextStep> nextsteps) {
		this.nextsteps = nextsteps;
	}

	public Set<ProjectProblem> getProblems() {
		return problems;
	}

	public void setProblems(Set<ProjectProblem> problems) {
		this.problems = problems;
	}

	public Set<ProjectPhase> getPhases() {
		return phases;
	}

	public void setPhases(Set<ProjectPhase> phases) {
		this.phases = phases;
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

	public Project(int id, String projectname, String objectives, ProjectStatusLight projectStatus,
			ProjectStatusLight projectStatusConfidence, ProjectStatusLight projectDeliveryConfidence,
			ProjectStatusLight projectGovernance, ProjectStatusLight projectBusinessChange,
			ProjectStatusLight projectBenefitsRealisation, ProjectStatusLight projectDependency,
			ProjectStatusLight projectResourcing, ProjectStatusLight projectScope, Set<ProjectAchievement> achievements,
			Set<ProjectEscalation> escalations, Set<ProjectNextStep> nextsteps, Set<ProjectProblem> problems,
			Set<ProjectPhase> phases, User user, Double tVC, Double tIC, Double oP, Double budgettodate,
			Double costestimated, Double eACOP, Double variance, Double certifiedprogress, Double invoiced) {
		super();
		this.id = id;
		this.projectname = projectname;
		this.objectives = objectives;
		this.projectStatus = projectStatus;
		this.projectStatusConfidence = projectStatusConfidence;
		this.projectDeliveryConfidence = projectDeliveryConfidence;
		this.projectGovernance = projectGovernance;
		this.projectBusinessChange = projectBusinessChange;
		this.projectBenefitsRealisation = projectBenefitsRealisation;
		this.projectDependency = projectDependency;
		this.projectResourcing = projectResourcing;
		this.projectScope = projectScope;
		this.achievements = achievements;
		this.escalations = escalations;
		this.nextsteps = nextsteps;
		this.problems = problems;
		this.phases = phases;
		this.user = user;
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

	public Project() {

	}
}
