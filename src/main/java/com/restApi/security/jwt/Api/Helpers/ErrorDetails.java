package com.restApi.security.jwt.Api.Helpers;

import java.util.Date;
import lombok.Data;

@Data
public class ErrorDetails {

    //pojo class for error handling purposes only. send the error object to the client
    
	private Date time;
	private String message;
	private String details;

	public ErrorDetails(Date time, String message, String details) {
		super();
                this.message= message;
		this.time = time;
		this.details = details;
	}

    public ErrorDetails() {
    }

	
}
