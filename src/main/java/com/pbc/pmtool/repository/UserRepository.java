package com.pbc.pmtool.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{
	
	public abstract User findByUsername(String username);
	
	/**
	 * Devuelve todos los usuarios
	 */
	public abstract Page<User> findAll(Pageable pageable);
	
	/**
	 * Devuelve los usuarios por username o name
	 * 
	 * @param pageable
	 * @param name
	 * @return Page<User>
	 */
	public abstract Page<User> findUsersByUsernameContainingOrNameContaining(String username, String name, Pageable pageable);
}