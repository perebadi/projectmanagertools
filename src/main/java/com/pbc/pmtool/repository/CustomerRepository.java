package com.pbc.pmtool.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.Customer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {

	public Customer findById(int id);
}
