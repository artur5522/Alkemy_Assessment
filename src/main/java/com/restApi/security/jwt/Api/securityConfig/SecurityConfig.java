package com.restApi.security.jwt.Api.securityConfig;

import com.restApi.security.jwt.Api.security.CustomUserDetailsService;
import com.restApi.security.jwt.Api.security.JwtAuthenticationEntryPoint;
import com.restApi.security.jwt.Api.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // for swagger ui
    private static final String[] AUTH_LIST_SWAGGER = {
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/webjars/**",
        "/v3/api-docs/**"
    };
    
    private static final String[] AUTH_LIST_GETPATHS={
        "/characters/getAll",
        "/movies/getAll",
        "/characters/getOne/**",
        "/characters/getOneFilteredByName",
        "/movies/getOneFilteredByName"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, AUTH_LIST_GETPATHS).permitAll()                   
                    .antMatchers(AUTH_LIST_SWAGGER).permitAll()
                    .antMatchers("/auth/**").permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //for AuthController class. Authenticates the user and pasword
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
