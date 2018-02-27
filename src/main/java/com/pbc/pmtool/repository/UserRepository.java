package com.pbc.pmtool.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.entity.UserRole;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{
	
	public abstract List<User> findByEnabled(boolean enabled);
	
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
	
	@Query("SELECT u FROM User u JOIN u.userRole r WHERE r.role=:userRole AND u.enabled is true")
	public abstract List<User> findByRole(@Param("userRole") String userRole);
	
}