package com.pbc.pmtool.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pbc.pmtool.entity.User;
import com.pbc.pmtool.entity.UserRole;
import com.pbc.pmtool.model.FormUserAdminModel;
import com.pbc.pmtool.repository.UserRepository;
import com.pbc.pmtool.service.UserService;






@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	private org.springframework.security.core.userdetails.User buildUser(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
				true, true, true, authorities);
		
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(UserRole  userRole : userRoles){
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
			
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	/**
	 * Devuelve todos los usuarios
	 */
	@Override
	public List<FormUserAdminModel> getAllUsers() {
		//Obtenemos todos los usuarios
		ArrayList<FormUserAdminModel> usersModel = new ArrayList<FormUserAdminModel>();
		
		//Recorremos todos los usuarios
		for(User userEntity : userRepository.findAll()) {
			
		}
		
		return usersModel;
	}
	
}
