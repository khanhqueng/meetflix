package com.khanhisdev.movieservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    @Column(unique = true)
    private String name;

    private String description;
    // by minutes
    private int durationMin;
    private List<String> director;
    private List<String> actors;
    private String urlImage;
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "movie_category",
            joinColumns= @JoinColumn( name = "movie_id") ,
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories;






}
