    package com.restApi.security.jwt.Api.service;

import com.restApi.security.jwt.Api.dto.CharacterDTO;
import java.util.List;

public interface CharacterService {

    CharacterDTO getCharacter(long characterId);

    List<CharacterDTO> getAllCharacters();

    CharacterDTO createCharacter(CharacterDTO characterDTO);

    CharacterDTO editCharacter(long characterId, CharacterDTO characterDTO);

    void deleteCharacter(long characterId);

    List<CharacterDTO> findByNameLike(String name);

    CharacterDTO addMovieToCharacter(long characterId, String movieTitle) throws Exception ;
    
    CharacterDTO removeMoviefromCharacter(long characterId, String movieTitle) throws Exception ;
}
