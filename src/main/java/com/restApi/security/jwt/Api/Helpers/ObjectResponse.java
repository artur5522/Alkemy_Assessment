

package com.restApi.security.jwt.Api.Helpers;

import lombok.Data;

@Data
public class ObjectResponse {

    //pojo class for certain responses to the client
    
    private String message;

    public ObjectResponse(String message) {
        this.message = message;
    }

    public ObjectResponse() {
    }
    
    
    
}