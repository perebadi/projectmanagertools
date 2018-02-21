package com.pbc.pmtool.enums;

public enum ProblemTypeEnum {
	COST("Cost"), TIME("Time"), QUALITY("Quality"), COSTANDTIME("Cost and time"), TIMEANDQUALITY("Time and quality");
	
	private String type;

	private ProblemTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
