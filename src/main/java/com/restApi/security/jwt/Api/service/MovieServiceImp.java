package com.restApi.security.jwt.Api.service;

import com.restApi.security.jwt.Api.dto.MovieDTO;
import com.restApi.security.jwt.Api.entities.Charact;
import com.restApi.security.jwt.Api.entities.Movie;
import com.restApi.security.jwt.Api.exeptions.CharacterMovieAssociationException;
import com.restApi.security.jwt.Api.exeptions.ResourceNotFoundException;
import com.restApi.security.jwt.Api.exeptions.SortMovieException;
import com.restApi.security.jwt.Api.repository.CharacterRepository;
import com.restApi.security.jwt.Api.repository.MovieRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public MovieDTO getMovie(long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
        return mapMovieDTO(movie);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieDTO> moviesDTO = movieRepository.findAll().stream()
                .map(movie -> mapMovieDTO(movie)).collect(Collectors.toList());
        return moviesDTO;
    }

    @Override
    public List<MovieDTO> findByNameLike(String name, String sortOrder) throws Exception {
        List<MovieDTO> moviesDTO = movieRepository.findByTitleContaining(name).stream()
                .map(movie -> mapMovieDTO(movie)).collect(Collectors.toList());
        if (sortOrder.equalsIgnoreCase("desc")) {
            moviesDTO = moviesDTO.stream()
                    .sorted(Comparator.comparing(MovieDTO::getCreationDate).reversed())
                    .collect(Collectors.toList());

        } else if (sortOrder.equalsIgnoreCase("asc")) {
            moviesDTO = moviesDTO.stream()
                    .sorted(Comparator.comparing(MovieDTO::getCreationDate))
                    .collect(Collectors.toList());
        } else {
            throw new SortMovieException("Sort Order must be 'asc' or 'desc'");
        }
        return moviesDTO;
    }
    
   
    @Override
    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = mapMovie(movieDTO);
        movie = movieRepository.save(movie);
        return mapMovieDTO(movie);
    }

    @Override
    public MovieDTO editMovie(long movieId, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        movie.setRate(movieDTO.getRate());
        movie.setCharacters(movieDTO.getCharacters());
        movie.setCreationDate(movieDTO.getCreationDate());
        movie.setTitle(movieDTO.getTitle());

        movie = movieRepository.save(movie);
        return mapMovieDTO(movie);
    }

    @Override
    public MovieDTO addCharacter(long movieId, String characterName) throws Exception {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
        Charact character = characterRepository.findByName(characterName)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "name", characterName));

        for (Charact c : movie.getCharacters()) {
            if (c.getName().equalsIgnoreCase(characterName)) {
                throw new CharacterMovieAssociationException("The movie -> ".concat(movie.getTitle()).
                        concat(" is already assigned to the character: ").concat(characterName));
            }
        }
        character.getMovies().add(movie);
        character = characterRepository.save(character);
        movie.getCharacters().add(character);
        return mapMovieDTO(movie);
    }

    @Override
    public MovieDTO removeCharacterFromMovie(long movieId, String characterName) throws Exception {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
        Charact character = characterRepository.findByName(characterName)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "name", characterName));

        boolean flag = false;
        for (Charact c : movie.getCharacters()) {
            if (c.getName().equalsIgnoreCase(characterName)) flag = true;
        }
        if (!flag) {
            throw new CharacterMovieAssociationException("The movie -> ".concat(movie.getTitle()).
                    concat(" does not have assigned the character: ").concat(characterName));
        }

        character.getMovies().remove(movie);
        character = characterRepository.save(character);
        movie.getCharacters().remove(character);
        return mapMovieDTO(movie);
    }

    @Override
    public void deleteMovie(long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
        movieRepository.delete(movie);
    }

    protected MovieDTO mapMovieDTO(Movie movie) {
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        return movieDTO;
    }

    protected Movie mapMovie(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        return movie;
    }

    
}
