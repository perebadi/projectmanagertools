package com.pbc.pmtool.component;

import org.springframework.stereotype.Component;

import com.pbc.pmtool.model.CustomerModel;
import com.pbc.pmtool.model.FormCustomerAddModel;

@Component("formCustomerAddConverter")
public class FormCustomerAddConverter {

	public CustomerModel FormCustomerAdd2CustomerModel(FormCustomerAddModel customerAddModel) {
		CustomerModel customerModel = new CustomerModel();
		
		customerModel.setCustomer(customerAddModel.getCustomer());
		
		return customerModel;
	}
	
}
