package com.restApi.security.jwt.Api;

import com.restApi.security.jwt.Api.security.JwtAuthenticationFilter;
import com.restApi.security.jwt.Api.Helpers.ObjectResponse;
import com.sendgrid.SendGrid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    //needed beans for correct functionality
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public ObjectResponse response() {
        return new ObjectResponse();
    }
    
    @Bean
    public SendGrid sendGrid(){
        return new SendGrid(appKey);
    }
    
    //acces to the property in application.properties. needed for JWT.
    //idealy this should be in a environment variable
    @Value("${app.sendgrid.key}")
    private String appKey;
    

}
