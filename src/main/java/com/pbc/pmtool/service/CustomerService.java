package com.pbc.pmtool.service;

import java.util.List;

import com.pbc.pmtool.model.CustomerModel;

public interface CustomerService {

	public List<CustomerModel> getAll();
	
	public CustomerModel getById(int id);
	
}
