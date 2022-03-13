package com.restApi.security.jwt.Api.service;

import com.restApi.security.jwt.Api.dto.CharacterDTO;
import com.restApi.security.jwt.Api.entities.Charact;
import com.restApi.security.jwt.Api.entities.Movie;
import com.restApi.security.jwt.Api.exeptions.CharacterMovieAssociationException;
import com.restApi.security.jwt.Api.exeptions.ResourceNotFoundException;
import com.restApi.security.jwt.Api.repository.CharacterRepository;
import com.restApi.security.jwt.Api.repository.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImp implements CharacterService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public CharacterDTO getCharacter(long characterId) {
        Charact character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));
        return mapCharacterDTO(character);
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {
        List<CharacterDTO> characterDTOs = characterRepository.findAll().stream()
                .map(character -> mapCharacterDTO(character)).collect(Collectors.toList());
        return characterDTOs;
    }

    @Override
    public List<CharacterDTO> findByNameLike(String name) {
        List<CharacterDTO> characters = characterRepository.findByNameContaining(name).stream()
                .map(character -> mapCharacterDTO(character)).collect(Collectors.toList());
        return characters;
    }

    @Override
    public CharacterDTO createCharacter(CharacterDTO characterDTO) {
        Charact character = mapCharacter(characterDTO);
        character = characterRepository.save(character);
        return mapCharacterDTO(character);
    }

    @Override
    public CharacterDTO editCharacter(long characterId, CharacterDTO characterDTO) {
        Charact character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));
        character.setName(characterDTO.getName());
        character.setDateOfBirth(characterDTO.getDateOfBirth());
        character.setMovies(characterDTO.getMovies());
        character.setHistory(characterDTO.getHistory());
        character.setWeight(characterDTO.getWeight());

        character = characterRepository.save(character);
        return mapCharacterDTO(character);

    }

    @Override
    public CharacterDTO addMovieToCharacter(long characterId, String movieTitle) throws Exception {

        Charact character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));
        Movie movie = movieRepository.findByTitle(movieTitle)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "title", movieTitle));

        for (Movie m : character.getMovies()) {
            if (m.getTitle().equalsIgnoreCase(movieTitle)) {
                throw new CharacterMovieAssociationException("The character -> ".concat(character.getName()).
                        concat(" is already assigned to the movie: ").concat(movieTitle));
            }
        }
        character.getMovies().add(movie);
        character = characterRepository.save(character);
        return mapCharacterDTO(character);
    }

    @Override
    public CharacterDTO removeMoviefromCharacter(long characterId, String movieTitle) throws Exception {
        Charact character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));
        Movie movie = movieRepository.findByTitle(movieTitle)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "title", movieTitle));
        boolean flag = false;
        for (Movie m : character.getMovies()) {
            if (m.getTitle().equalsIgnoreCase(movieTitle)) flag = true;
        }
        if (!flag) {
            throw new CharacterMovieAssociationException("The character -> ".concat(character.getName()).
                    concat(" is not assigned to the movie: ").concat(movieTitle));
        }
        character.getMovies().remove(movie);
        character = characterRepository.save(character);
        return mapCharacterDTO(character);
    }

    @Override
    public void deleteCharacter(long characterId) {
        Charact character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResourceNotFoundException("Character", "id", characterId));
        characterRepository.delete(character);
    }

    protected CharacterDTO mapCharacterDTO(Charact character) {
        CharacterDTO characterDTO = modelMapper.map(character, CharacterDTO.class);
        return characterDTO;
    }

    protected Charact mapCharacter(CharacterDTO characterDTO) {
        Charact character = modelMapper.map(characterDTO, Charact.class);
        return character;
    }

}
