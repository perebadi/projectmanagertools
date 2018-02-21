package com.pbc.pmtool.enums;

public enum ProblemResponsableEnum {
	DXC("DxC"),CUSTOMER("Customer"),DXCANDCUSTOMER("DxC and customer");
	
	private String responsable;

	public String getResponsable() {
		return responsable;
	}

	private ProblemResponsableEnum(String responsable) {
		this.responsable = responsable;
	}
	
}
