package com.pbc.pmtool.enums;

public enum RiskStrategyEnum {
	ACCEPT("Accept"), MITIGATE("Mitigate");
	
	private String strategy;

	public String getStrategy() {
		return strategy;
	}

	private RiskStrategyEnum(String strategy) {
		this.strategy = strategy;
	}
	
}
