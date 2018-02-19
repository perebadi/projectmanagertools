package com.pbc.pmtool.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.component.CustomerConverter;
import com.pbc.pmtool.entity.Customer;
import com.pbc.pmtool.model.CustomerModel;
import com.pbc.pmtool.repository.CustomerRepository;
import com.pbc.pmtool.service.CustomerService;

@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	@Qualifier("customerRepository")
	private CustomerRepository customerRepository;
	
	@Autowired
	@Qualifier("customerConverter")
	private CustomerConverter customerConverter;
	
	@Override
	public List<CustomerModel> getAll() {
		List<CustomerModel> customers = new ArrayList<CustomerModel>();
		
		for(Customer customer : customerRepository.findAll()) {
			customers.add(customerConverter.Customer2CustomerModel(customer));
		}
		
		return customers;
	}

	@Override
	public CustomerModel getById(int id) {
		Customer customerEntity = customerRepository.findById(id);
		
		if(customerEntity != null) {
			return customerConverter.Customer2CustomerModel(customerEntity);
		}
		
		return null;
	}

	
}
