package com.restApi.security.jwt.Api.service;

import com.restApi.security.jwt.Api.dto.MovieDTO;
import java.util.List;

public interface MovieService {

    MovieDTO getMovie(long movieId);

    List<MovieDTO> getAllMovies();

    List<MovieDTO> findByNameLike(String name, String sortOrder) throws Exception;

    MovieDTO createMovie(MovieDTO movieDTO);

    MovieDTO editMovie(long movieId, MovieDTO movieDTO);

    MovieDTO addCharacter(long movieId, String characterName) throws Exception;

    MovieDTO removeCharacterFromMovie(long movieId, String characterName) throws Exception;

    void deleteMovie(long movieId);
}
