package com.restApi.security.jwt.Api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity()
public class Charact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    private LocalDate dateOfBirth;

    private short weight;

    private String history;

    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "characters_movies",
            joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<Movie> movies;

    public Charact() {
    }

}
