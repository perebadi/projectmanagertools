package com.pbc.pmtool.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentModel implements Serializable, Comparable<CommentModel> {
	
	private int id;
	private String detail;
	private String datecomment;
	
	public CommentModel() {}
	
	public CommentModel(String detail, String datecomment) {
		super();
		this.detail = detail;
		this.datecomment = datecomment;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatecomment() {
		return datecomment;
	}

	public void setDatecomment(String datecomment) {
		this.datecomment = datecomment;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public int compareTo(CommentModel o) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date thisFecha = null;
		Date oFecha = null;
		
		try {
			thisFecha = dateFormatter.parse(this.getDatecomment());
			oFecha = dateFormatter.parse(o.getDatecomment());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(thisFecha.before(oFecha)) {
			return 1;
		}else if(thisFecha.after(oFecha)) {
			return -1;
		}else {
			if(this.getId() < o.getId()) {
				return 1;
			}else {
				return -1;
			}
		}
	}
	
	
	
}
