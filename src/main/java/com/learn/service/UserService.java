package com.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.model.User;
import com.learn.repository.UserRepository;

@Service
@Profile(value = {"!dev","prod"})
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	public User addUserByAdmin(User user) {

		String pwd = user.getPassword();
		String encryptPwd = bCryptEncoder.encode(pwd);
		user.setPassword(encryptPwd);
		return userRepository.save(user);

	}

}
