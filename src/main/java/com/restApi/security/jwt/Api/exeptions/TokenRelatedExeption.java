package com.restApi.security.jwt.Api.exeptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TokenRelatedExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus state;
	private String message;

	public TokenRelatedExeption(HttpStatus state, String message) {
		super();
		this.state = state;
		this.message = message;
	}

	

}
