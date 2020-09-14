package com.myapp.crud.appgen.security.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapp.crud.appgen.endUser.EndUser;
import com.myapp.crud.appgen.endUser.EndUserDAO;
import com.myapp.crud.appgen.endUser.EndUserService;
import com.myapp.crud.appgen.security.CustomUserDetails;

//@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<CustomUserDetails> inMemoryUserList = new ArrayList<>();

	static {
		inMemoryUserList.add(new CustomUserDetails(1L, "in28minutes",
				"$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
		inMemoryUserList.add(new CustomUserDetails(1L, "test",
				"$2a$10$fdxH3igDJy0ZKUdpKWtAsuri0GRed6sO14NTxchvC5PdyztTD4ztm", "ROLE_USER_2"));

	}


	public JwtInMemoryUserDetailsService() {
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();
		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}
		return findFirst.get();
	}

}
