package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{
	
	public abstract User findByUsername(String username);
	
	/**
	 * Devuelve todos los usuarios
	 */
	public abstract List<User> findAll();
}