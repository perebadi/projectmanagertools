package com.pbc.pmtool.enums;

public enum ProblemStatusEnum {
	OPEN("Open"), CLOSED("Closed");
	
	private String status;
	 
	ProblemStatusEnum(String status) {
        this.status = status;
    }
 
    public String getStatus() {
        return status;
    }
}
