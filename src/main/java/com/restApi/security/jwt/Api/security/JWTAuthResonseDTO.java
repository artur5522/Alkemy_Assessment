package com.restApi.security.jwt.Api.security;

import lombok.Data;

@Data
public class JWTAuthResonseDTO {

	private String accesToken;
	private String tokenType = "Bearer";

	public JWTAuthResonseDTO(String accesToken) {
		super();
		this.accesToken = accesToken;
	}

	public JWTAuthResonseDTO(String accesToken, String tokenType) {
		super();
		this.accesToken = accesToken;
		this.tokenType = tokenType;
	}

	

}
