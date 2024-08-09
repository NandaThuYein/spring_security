package com.token.spring_security.dto;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {

	private String token;
	
	private String refreshToken;
}
