

package com.restApi.security.jwt.Api.repository;

import com.restApi.security.jwt.Api.entities.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>{

    
    List<Movie> findByTitleContaining(String name);
    
    Optional<Movie> findByTitle(String title);
}
