package com.pbc.pmtool.model;

public class FormCommentModel {
	private int idcomment;
	private String comment;

	public FormCommentModel() {
	}

	public FormCommentModel(int idcomment, String comment) {
		super();
		this.idcomment = idcomment;
		this.comment = comment;
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
