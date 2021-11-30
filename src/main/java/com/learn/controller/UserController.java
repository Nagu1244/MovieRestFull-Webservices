package com.learn.controller;

import java.awt.PageAttributes.MediaType;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.sym.Name;
import com.learn.model.User;
import com.learn.service.UserService;

@RestController
@RequestMapping(value = "/admin")

public class UserController {
	@Autowired
	
	private UserService userService;

	@PostMapping(value = "/adduser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		
		User aa=userService.addUserByAdmin(user);
		return new ResponseEntity<User>(aa,HttpStatus.CREATED);
        
	}
}
