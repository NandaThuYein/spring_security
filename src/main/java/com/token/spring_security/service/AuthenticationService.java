package com.token.spring_security.service;

import com.token.spring_security.dto.JWTAuthenticationResponse;
import com.token.spring_security.dto.SignUpRequest;
import com.token.spring_security.dto.SigninRequest;
import com.token.spring_security.entities.User;

public interface AuthenticationService {

	User signup(SignUpRequest signUpRequest);
	
	JWTAuthenticationResponse signin(SigninRequest signinRequest);
}
