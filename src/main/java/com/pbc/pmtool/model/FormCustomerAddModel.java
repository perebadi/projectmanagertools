package com.pbc.pmtool.model;

import org.hibernate.validator.constraints.NotEmpty;

public class FormCustomerAddModel {

	@NotEmpty
	private String customer;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public FormCustomerAddModel(String customer) {
		super();
		this.customer = customer;
	}
	
	public FormCustomerAddModel() {}
	
}
