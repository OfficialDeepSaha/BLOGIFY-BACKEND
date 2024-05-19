package com.blog.api.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.api.Entity.User;
import com.blog.api.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public CustomUserDetailService () {
		
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
	    User user 	= userRepository.findByEmail(email);
	    if (user==null) {
	    	
	    throw new UsernameNotFoundException(email + "This email is not found!");
			
		}
		
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    
	    
	    return new org.springframework.security.core.userdetails.User(user.getEmail() , user.getPassword() , authorities);
		
	}

}
