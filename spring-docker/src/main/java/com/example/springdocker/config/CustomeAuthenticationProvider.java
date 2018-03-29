package com.example.springdocker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.springdocker.service.UserDetailsServiceImpl;

@Component
public class CustomeAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	private void setUserDetailsSerive(UserDetailsServiceImpl userDetailsService){
		super.setUserDetailsService(userDetailsService);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return super.authenticate(authentication);
	}
	

}
