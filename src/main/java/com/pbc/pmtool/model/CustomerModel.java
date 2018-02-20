package com.pbc.pmtool.model;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerModel {

	private int id;
	@NotEmpty
	private String customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

}
