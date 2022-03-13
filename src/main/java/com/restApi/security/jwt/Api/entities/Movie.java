package com.restApi.security.jwt.Api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String title;

    private double rate;

    private Integer creationDate;
    
    @JsonBackReference
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "movies", targetEntity = Charact.class, fetch = FetchType.EAGER)
    private List<Charact> characters;
    
        
    public Movie() {
    }

}
