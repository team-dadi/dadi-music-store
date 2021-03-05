package com.hcl.dadimusicwebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hcl.dadimusicwebapp.model.User;
import com.hcl.dadimusicwebapp.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username) {
		final User user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserPrincipal(user);
	}
}