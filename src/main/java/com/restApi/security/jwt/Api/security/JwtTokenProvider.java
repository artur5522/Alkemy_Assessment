package com.restApi.security.jwt.Api.security;

import com.restApi.security.jwt.Api.exeptions.TokenRelatedExeption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
    
	@Value("${app.jwt-secret}")//get propertiy from application.properties
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")//get propertiy from application.properties
	private int jwtExpirationInMs;
	
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date presentDate = new Date();
		Date expirationDate = new Date(presentDate.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder()
                                .setSubject(username)
                                .setIssuedAt(new Date())
                                .setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
                                .compact();
		
		return token;
	}
        
	
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(token)
                        .getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch (SignatureException ex) {
			throw new TokenRelatedExeption(HttpStatus.BAD_REQUEST,"JWT signature invalid");
		}
                catch (MalformedJwtException ex) {
			throw new TokenRelatedExeption(HttpStatus.BAD_REQUEST,"Wronge JWT structure");
		}
		catch (ExpiredJwtException ex) {
			throw new TokenRelatedExeption(HttpStatus.BAD_REQUEST,"JWT expired");
		}
		catch (UnsupportedJwtException ex) {
			throw new TokenRelatedExeption(HttpStatus.BAD_REQUEST,"Unsupported JWT ");
		}
		catch (IllegalArgumentException ex) {
			throw new TokenRelatedExeption(HttpStatus.BAD_REQUEST,"JWT is empty");
		}
	}
}

