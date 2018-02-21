package com.pbc.pmtool.enums;

public enum RiskProbabilityEnum {
	HIGH("High"),MEDIUM("Medium"),LOW("Low");
	
	private String probability;

	private RiskProbabilityEnum(String probability) {
		this.probability = probability;
	}

	public String getProbability() {
		return probability;
	}
	
}
