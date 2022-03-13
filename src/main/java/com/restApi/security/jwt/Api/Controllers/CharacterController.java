package com.restApi.security.jwt.Api.Controllers;

import com.restApi.security.jwt.Api.Helpers.ObjectResponse;
import com.restApi.security.jwt.Api.dto.CharacterDTO;
import com.restApi.security.jwt.Api.service.CharacterService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @Autowired
    ObjectResponse response;

    @GetMapping("/getAll")
    public ResponseEntity<List<CharacterDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharacters());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<CharacterDTO> getOne(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getCharacter(id));
    }
    
    @GetMapping("/getOneFilteredByName")
    public ResponseEntity<List<CharacterDTO>> getOneByNameLike(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.findByNameLike(name));
    }

    @PostMapping("/create")
    public ResponseEntity<CharacterDTO> createCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.createCharacter(characterDTO));
    }

    @PutMapping("/editCharacter/{id}")
    public ResponseEntity<CharacterDTO> editCharacter(@Valid @RequestBody CharacterDTO characterDTO, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.editCharacter(id, characterDTO));
    }
    
    @PutMapping("/addMovie/{characterId}")
    public ResponseEntity<CharacterDTO> addMovieToCharacter(@RequestParam String movieTitle,@PathVariable int characterId ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.addMovieToCharacter(characterId, movieTitle));
    }
    
    @PutMapping("/removeMovie/{characterId}")
    public ResponseEntity<CharacterDTO> removeMovieFromCharacter(@RequestParam String movieTitle,@PathVariable int characterId ) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.removeMoviefromCharacter(characterId, movieTitle));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ObjectResponse> deleteCharacter(@PathVariable long id) {
        characterService.deleteCharacter(id);
        response = new ObjectResponse("Character deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
    
}
