package com.restApi.security.jwt.Api.exeptions;

import lombok.Data;

@Data
public class SortMovieException extends RuntimeException {

    private static final long serialVersionUID = 1L;
  
    private String message;

    public SortMovieException(String message) {
        super();
        this.message = message;
    }
    

}
