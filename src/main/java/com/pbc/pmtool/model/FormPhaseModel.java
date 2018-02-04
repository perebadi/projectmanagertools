package com.pbc.pmtool.model;

public class FormPhaseModel {

	private int idphase;
	private String startdate;
	private String enddate;
	private String newdate;
	private String summaryphase;
	private int weekdelay;
	private int progress;
	private int rag;

	public int getIdphase() {
		return idphase;
	}

	public void setIdphase(int idphase) {
		this.idphase = idphase;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getNewdate() {
		return newdate;
	}

	public void setNewdate(String newdate) {
		this.newdate = newdate;
	}

	public String getSummaryphase() {
		return summaryphase;
	}

	public void setSummaryphase(String summaryphase) {
		this.summaryphase = summaryphase;
	}

	public int getWeekdelay() {
		return weekdelay;
	}

	public void setWeekdelay(int weekdelay) {
		this.weekdelay = weekdelay;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getRag() {
		return rag;
	}

	public void setRag(int rag) {
		this.rag = rag;
	}



	public FormPhaseModel(int idphase, String startdate, String enddate, String newdate, String summaryphase, int weekdelay,
			int progress, int rag) {
		super();
		this.idphase = idphase;
		this.startdate = startdate;
		this.enddate = enddate;
		this.newdate = newdate;
		this.summaryphase = summaryphase;
		this.weekdelay = weekdelay;
		this.progress = progress;
		this.rag = rag;
	}

	public FormPhaseModel() {

	}

}
