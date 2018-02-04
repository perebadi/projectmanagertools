package com.pbc.pmtool.model;

public class SumValuesModel {

	private double sTVC;
	private double sTIC;
	private double scostestimated;
	private double OP;
	private double EACOP;

	public double getsTVC() {
		return sTVC;
	}

	public void setsTVC(double sTVC) {
		this.sTVC = sTVC;
	}

	public double getsTIC() {
		return sTIC;
	}

	public void setsTIC(double sTIC) {
		this.sTIC = sTIC;
	}

	public double getScostestimated() {
		return scostestimated;
	}

	public void setScostestimated(double scostestimated) {
		this.scostestimated = scostestimated;
	}
	
	
	

	public double getOP() {
		return OP;
	}

	public void setOP(double oP) {
		OP = oP;
	}

	public double getEACOP() {
		return EACOP;
	}

	public void setEACOP(double eACOP) {
		EACOP = eACOP;
	}

	

	public SumValuesModel(double sTVC, double sTIC, double scostestimated, double oP, double eACOP) {
		super();
		this.sTVC = sTVC;
		this.sTIC = sTIC;
		this.scostestimated = scostestimated;
		OP = oP;
		EACOP = eACOP;
	}

	public SumValuesModel() {

	}

}
