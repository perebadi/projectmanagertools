package com.pbc.pmtool;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class pwd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.println(pe.encode("josep"));
	}

}
