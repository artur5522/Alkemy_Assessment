package com.restApi.security.jwt.Api.Controllers;

import com.restApi.security.jwt.Api.Helpers.ObjectResponse;
import com.restApi.security.jwt.Api.dto.MovieDTO;
import com.restApi.security.jwt.Api.service.MovieService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    ObjectResponse response;

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<MovieDTO> getOne(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovie(id));
    }

    @GetMapping("/getOneFilteredByName")
    public ResponseEntity<List<MovieDTO>> getOneByNameLike(@RequestParam String title, @RequestParam String order) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findByNameLike(title, order));
    }

    @PostMapping("/create")
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovie(movieDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<MovieDTO> editMovie(@Valid @RequestBody MovieDTO movieDTO, @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.editMovie(id, movieDTO));
    }

    @PutMapping("/addCharacter/{movieId}")
    public ResponseEntity<MovieDTO> addCharacter(@PathVariable long movieId, @RequestParam String CharacterName) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.addCharacter(movieId, CharacterName));
    }

    @PutMapping("/removeCharacter/{movieId}")
    public ResponseEntity<MovieDTO> removeCharacterFromMovie(@PathVariable long movieId, @RequestParam String CharacterName) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.removeCharacterFromMovie(movieId, CharacterName));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ObjectResponse> deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        response = new ObjectResponse("Movie deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
