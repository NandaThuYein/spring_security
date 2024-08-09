package com.token.spring_security.dto;

import lombok.Data;

@Data
public class SignUpRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
}