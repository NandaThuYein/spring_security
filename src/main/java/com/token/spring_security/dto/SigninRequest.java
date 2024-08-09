package com.token.spring_security.dto;

import lombok.Data;

@Data
public class SigninRequest {

	private String email;
	
	private String password;
}
