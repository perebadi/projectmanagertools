package com.pbc.pmtool.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.pbc.pmtool.enums.ProblemImpactEnum;
import com.pbc.pmtool.enums.ProblemResponsableEnum;
import com.pbc.pmtool.enums.ProblemStatusEnum;
import com.pbc.pmtool.enums.ProblemTypeEnum;



@Entity
@Table(name = "projectproblem")
public class ProjectProblem implements Comparable<ProjectProblem> {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable=false)
	private int id;
	
	@NotNull
	@Column(name = "dateproblem", nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateproblem;
	
	@NotNull
	@Column(name="week", nullable=false)
	private int week;
	
	@NotNull
	@Column(name="summaryproblem", nullable=false)
	private String summaryproblem;
	
	@Column(name="txtproblem")
	private String txtproblem;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="status", length=6, nullable=false)
	private ProblemStatusEnum status;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="responsable", length=15, nullable=false)
	private ProblemResponsableEnum responsable;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="impact")
	private ProblemImpactEnum impact;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private ProblemTypeEnum type;
	
	@NotNull
	@Column(name="actions")
	private String actions;
	
	@Column(name = "dateclose")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateclose;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Project project;
	
	public ProblemStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProblemStatusEnum status) {
		this.status = status;
	}

	public ProblemResponsableEnum getResponsable() {
		return responsable;
	}

	public void setResponsable(ProblemResponsableEnum responsable) {
		this.responsable = responsable;
	}

	public ProblemImpactEnum getImpact() {
		return impact;
	}

	public void setImpact(ProblemImpactEnum impact) {
		this.impact = impact;
	}

	public ProblemTypeEnum getType() {
		return type;
	}

	public void setType(ProblemTypeEnum type) {
		this.type = type;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public Date getDateclose() {
		return dateclose;
	}

	public void setDateclose(Date dateclose) {
		this.dateclose = dateclose;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateproblem() {
		return dateproblem;
	}

	public void setDateproblem(Date dateproblem) {
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public ProjectProblem(int id, Date dateproblem, int week, String summaryproblem, String txtproblem,
			ProblemStatusEnum status, ProblemResponsableEnum responsable, ProblemImpactEnum impact,
			ProblemTypeEnum type, String actions, Date dateclose, Project project) {
		super();
		this.id = id;
		this.dateproblem = dateproblem;
		this.week = week;
		this.summaryproblem = summaryproblem;
		this.txtproblem = txtproblem;
		this.status = status;
		this.responsable = responsable;
		this.impact = impact;
		this.type = type;
		this.actions = actions;
		this.dateclose = dateclose;
		this.project = project;
	}

	public ProjectProblem() {
		
	}

	@Override
	public int compareTo(ProjectProblem o) {
		if(this.getDateproblem().before(o.getDateproblem())) {
			return -1;
		}else if(this.getDateproblem().after(o.getDateproblem())) {
			return 1;
		}else {
			if(this.getId() < o.getId()) {
				return -1;
			}else {
				return 1;
			}
		}
	}

}
