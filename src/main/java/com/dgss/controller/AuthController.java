package com.dgss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dgss.dto.LoginDTO;
import com.dgss.dto.UsersDTO;
import com.dgss.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// POST store the user in db
	@PostMapping("/register")
	public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO userDto) {

		return new ResponseEntity<UsersDTO>(usersService.createUser(userDto), HttpStatus.CREATED);
	}
    
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<String>("User Logged In Successfully", HttpStatus.OK);
	}
}
