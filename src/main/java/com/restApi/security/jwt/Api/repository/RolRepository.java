package com.restApi.security.jwt.Api.repository;

import com.restApi.security.jwt.Api.entities.Rol;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByName(String nombre);
	
}
