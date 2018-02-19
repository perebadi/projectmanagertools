package com.pbc.pmtool.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "customer")
	private String customer;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Project> projects;

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

	public Customer(int id, String customer, Set<Project> projects) {
		super();
		this.id = id;
		this.customer = customer;
		this.projects = projects;
	}

	public Customer() {
	}

}
