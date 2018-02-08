package com.pbc.pmtool.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "user_role", uniqueConstraints=@UniqueConstraint(columnNames = {"role","username"}))
public class UserRole {
	
	@Id
	@GeneratedValue
	@Column(name = "userRoleId", unique=true, nullable=false)
	private Integer userRoleId;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="username", nullable=false)
	private User user;
	
	@Column(name = "role", nullable=false, length=45)
	private String role;
	
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}
	public UserRole(){}	
}
