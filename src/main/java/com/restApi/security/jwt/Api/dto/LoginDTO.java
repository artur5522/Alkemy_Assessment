package com.restApi.security.jwt.Api.dto;

import lombok.Data;

@Data
public class LoginDTO {

	private String usernameOrEmail;
	private String password;

}
