package com.pbc.pmtool.enums;

public enum ProblemResponsableEnum {
	DXC("DxC"),CUSTOMER("Customer"),DXCANDCUSTOMER("DxC and customer"), OUTSIDE("Outside");
	
	private String responsable;

	public String getResponsable() {
		return responsable;
	}

	private ProblemResponsableEnum(String responsable) {
		this.responsable = responsable;
	}
	
}
