

package com.restApi.security.jwt.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restApi.security.jwt.Api.entities.Charact;
import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Charact, Long>{
    
    List<Charact> findByNameContaining(String name);
    
    Optional<Charact> findByName(String name);
    
}
