package com.restApi.security.jwt.Api.exeptions;

import lombok.Data;

@Data
public class CharacterMovieAssociationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    public CharacterMovieAssociationException(String message) {
        super();
        this.message = message;
    }

}
