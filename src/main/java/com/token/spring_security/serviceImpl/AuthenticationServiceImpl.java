package com.token.spring_security.serviceImpl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.token.spring_security.dto.JWTAuthenticationResponse;
import com.token.spring_security.dto.SignUpRequest;
import com.token.spring_security.dto.SigninRequest;
import com.token.spring_security.entities.Role;
import com.token.spring_security.entities.User;
import com.token.spring_security.repository.UserRepository;
import com.token.spring_security.service.AuthenticationService;
import com.token.spring_security.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JWTService jwtService;
	
	public User signup(SignUpRequest signUpRequest) {
		User user = new User();
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstname(signUpRequest.getFirstName());
		user.setSecondname(signUpRequest.getLastName());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
	}
	
	public JWTAuthenticationResponse signin(SigninRequest signinRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
				signinRequest.getPassword()));
		
		var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid emailor password"));		
		var jwt = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
		
		
		JWTAuthenticationResponse jwtAuthentication = new JWTAuthenticationResponse();
		
		jwtAuthentication.setToken(jwt);
		jwtAuthentication.setRefreshToken(refreshToken);
		return jwtAuthentication;
	}
}










