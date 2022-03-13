package com.restApi.security.jwt.Api.dto;

import com.restApi.security.jwt.Api.entities.Charact;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieDTO {

    private long id;

    @NotEmpty(message = "movie title can not be empty")
    @Size(max = 100, message = "The movie title can not have more than 100 characters")
    private String title;

    @NotNull(message = "rate can not be empty")
    @Min(message = "rate con not be less than 1", value = 1)
    @Max(message = "rate can not be bigger than 10", value = 10)
    private Double rate;

    private List<Charact> characters;

       
    @NotNull(message = "creation date can not be null")
    @Min(message = "rate con not be less than 1899", value = 1899)
    @Max(message = "rate can not be bigger than 2030", value = 2030)
    private Integer creationDate;

    public MovieDTO() {
    }

}
