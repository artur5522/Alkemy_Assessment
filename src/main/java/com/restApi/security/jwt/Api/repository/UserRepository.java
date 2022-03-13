package com.restApi.security.jwt.Api.repository;

import com.restApi.security.jwt.Api.entities.Users;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long>{

	public Optional<Users> findByEmail(String email);
	
	public Optional<Users> findByUsernameOrEmail(String username,String email);
	
	public Optional<Users> findByUsername(String username);

	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
}
