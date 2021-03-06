package com.pbc.pmtool.model;

import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.pbc.pmtool.entity.Project;
import com.pbc.pmtool.entity.User;

public class FormShowTaskModel {

	private int id;
	private String summary;
	private String details;
	private Project project;
	private User user;
	private float realvsestimated;
	private int estimatedhours;
	private int hours;
	private List<CommentModel> comments;
	private int status;

	public FormShowTaskModel() {
	}

	public FormShowTaskModel(int id, String summary, String details, Project project, User user, float realvsestimated,
			int estimatedhours, int hours) {
		super();
		this.id = id;
		this.summary = summary;
		this.details = details;
		this.project = project;
		this.user = user;
		this.realvsestimated = realvsestimated;
		this.estimatedhours = estimatedhours;
		this.hours = hours;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

	
	public String getCommentsJSON() {
		Gson gson = new Gson();
		
		List<CommentModel> comments = getComments();
		
		Collections.sort(comments);
		
		return gson.toJson(comments);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getRealvsestimated() {
		return realvsestimated;
	}

	public void setRealvsestimated(float realvsestimated) {
		this.realvsestimated = realvsestimated;
	}

	public int getEstimatedhours() {
		return estimatedhours;
	}

	public void setEstimatedhours(int estimatedhours) {
		this.estimatedhours = estimatedhours;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
}
