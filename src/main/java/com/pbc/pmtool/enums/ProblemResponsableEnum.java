package com.pbc.pmtool.enums;

public enum ProblemResponsableEnum {
	DXC("Dxc"),CUSTOMER("Customer"),DXCANDCUSTOMER("Dxc and customer");
	
	private String responsable;

	public String getResponsable() {
		return responsable;
	}

	private ProblemResponsableEnum(String responsable) {
		this.responsable = responsable;
	}
	
}
