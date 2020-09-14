package com.myapp.crud.appgen.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.endUser.EndUser;
import com.myapp.crud.appgen.endUser.EndUserDAO;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private EndUserDAO endUserDAO;

	@Autowired
	public CustomUserDetailsService(EndUserDAO endUserDAO) {
		this.endUserDAO = endUserDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		EndUser endUser = endUserDAO.findByUserName(userName);
		if (endUser == null) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", userName));
		}
		
		return new CustomUserDetails(endUser, "ROLE_USER_2");
	}

}
