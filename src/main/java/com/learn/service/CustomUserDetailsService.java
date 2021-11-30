package com.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learn.model.User;
import com.learn.repository.UserRepository;

@Service

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(username);
		CustomUserDetails customDetail=null;
		if(user!=null)
		{
			customDetail=new CustomUserDetails();
			customDetail.setUser(user);
		}
		else
		{
			throw new UsernameNotFoundException("User is not found with name :"+username);
		}
		return customDetail;
	}

}
