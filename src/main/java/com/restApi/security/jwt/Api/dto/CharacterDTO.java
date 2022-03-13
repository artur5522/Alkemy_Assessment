

package com.restApi.security.jwt.Api.dto;

import com.restApi.security.jwt.Api.entities.Movie;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CharacterDTO {

    private long id;

    @NotEmpty(message = "name can not be null")
    @Size(max = 250, min = 3, message = "The character name can not have more than 80 characters or less than 3")
    private String name;
    
    @Past(message = "date can not be from the present or future. check again the date of birth")
    @NotNull(message = "date of birth can not be null")
    private LocalDate dateOfBirth;
    
    private short weight = 0;
    
    @Size(max = 200, message = "The character history can not have more than 240 characters")
    private String history;

    private List<Movie> movies;

    public CharacterDTO() {
    }
    
}
