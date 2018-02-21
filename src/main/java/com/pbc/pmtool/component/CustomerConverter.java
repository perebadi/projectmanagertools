package com.pbc.pmtool.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pbc.pmtool.entity.Customer;
import com.pbc.pmtool.model.CustomerModel;
import com.pbc.pmtool.repository.CustomerRepository;

@Component("customerConverter")
public class CustomerConverter {

	@Autowired
	@Qualifier("customerRepository")
	private CustomerRepository customerRepository;
	
	public CustomerModel Customer2CustomerModel(Customer customerEntity) {
		CustomerModel customerModel = new CustomerModel();
		
		customerModel.setId(customerEntity.getId());
		customerModel.setCustomer(customerEntity.getCustomer());
		
		return customerModel;
	}
	
	public Customer CustomerModel2Customer(CustomerModel customerModel) {
		Customer customerEntity = customerRepository.findById(customerModel.getId());
		
		if(customerEntity == null) {
			customerEntity = new Customer();
		}
		
		customerEntity.setCustomer(customerModel.getCustomer());
		
		return customerEntity;
	}
}
