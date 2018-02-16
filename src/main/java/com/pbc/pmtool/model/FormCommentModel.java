package com.pbc.pmtool.model;

import java.util.List;

public class FormCommentModel {
	private int idcomment;
	private String comment;
	private List<String> tags;

	public FormCommentModel() {
	}

	public FormCommentModel(int idcomment, String comment, List<String> tags) {
		super();
		this.idcomment = idcomment;
		this.comment = comment;
		this.tags = tags;
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getIdcomment() {
		return idcomment;
	}

	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
