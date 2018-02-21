package com.pbc.pmtool.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

	@Column(name = "projectactive")
	private boolean projectactive = true;
	
	@Column(name = "WBS")
	private String wbs;

	@Column(name = "objectives", columnDefinition = "TEXT")
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

	  @ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      },
      mappedBy = "assigneds")
	private List<User> assigneds;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User pmo;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
	private Set<Task> tasks = new HashSet<Task>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy="project")
	private Set<ProjectComment> comments = new HashSet<ProjectComment>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Set<ProjectComment> getComments() {
		return comments;
	}



	public void setComments(Set<ProjectComment> comments) {
		this.comments = comments;
	}



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



	public boolean isProjectactive() {
		return projectactive;
	}



	public void setProjectactive(boolean projectactive) {
		this.projectactive = projectactive;
	}



	public String getObjectives() {
		return objectives;
	}



	public void setObjectives(String objectives) {
		this.objectives = objectives;
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

	public Set<Problem> getProjectProblems(){
		Set<Problem> prjproblems = new HashSet<Problem>();
		
		for(ProjectProblem problem : problems) {
			if(problem instanceof Problem) {
				prjproblems.add((Problem) problem);
			}
		}
		
		return prjproblems;
	}
	
	public Set<Risk> getProjectRisks(){
		Set<Risk> risks = new HashSet<Risk>();
		
		for(ProjectProblem problem : problems) {
			if(problem instanceof Risk) {
				risks.add((Risk) problem);
			}
		}
		
		return risks;
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



	public List<User> getAssigneds() {
		return assigneds;
	}



	public void setAssigneds(List<User> assigneds) {
		this.assigneds = assigneds;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
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



	public Set<Task> getTasks() {
		return tasks;
	}



	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}





	public String getWbs() {
		return wbs;
	}



	public void setWbs(String wbs) {
		this.wbs = wbs;
	}



	public User getPmo() {
		return pmo;
	}



	public void setPmo(User pmo) {
		this.pmo = pmo;
	}



	public Project(int id, String projectname, boolean projectactive, String wbs, String objectives,
			ProjectStatusLight projectStatus, ProjectStatusLight projectStatusConfidence,
			ProjectStatusLight projectDeliveryConfidence, ProjectStatusLight projectGovernance,
			ProjectStatusLight projectBusinessChange, ProjectStatusLight projectBenefitsRealisation,
			ProjectStatusLight projectDependency, ProjectStatusLight projectResourcing, ProjectStatusLight projectScope,
			Set<ProjectAchievement> achievements, Set<ProjectEscalation> escalations, Set<ProjectNextStep> nextsteps,
			Set<ProjectProblem> problems, Set<ProjectPhase> phases, List<User> assigneds, User user, User pmo,
			Double tVC, Double tIC, Double oP, Double budgettodate, Double costestimated, Double eACOP, Double variance,
			Double certifiedprogress, Double invoiced, Set<Task> tasks, Set<ProjectComment> comments) {
		super();
		this.id = id;
		this.projectname = projectname;
		this.projectactive = projectactive;
		this.wbs = wbs;
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
		this.assigneds = assigneds;
		this.user = user;
		this.pmo = pmo;
		TVC = tVC;
		TIC = tIC;
		OP = oP;
		this.budgettodate = budgettodate;
		this.costestimated = costestimated;
		EACOP = eACOP;
		this.variance = variance;
		this.certifiedprogress = certifiedprogress;
		this.invoiced = invoiced;
		this.tasks = tasks;
		this.comments = comments;
	}



	public Project() {

	}
}
