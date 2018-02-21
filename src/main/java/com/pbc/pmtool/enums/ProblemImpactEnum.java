package com.pbc.pmtool.enums;

public enum ProblemImpactEnum {
	LOW("Low"),MEDIUM("Medium"),HIGH("High");
	
	private String impact;
	 
	ProblemImpactEnum(String impact) {
        this.impact = impact;
    }
 
    public String getImpact() {
        return impact;
    }
}
