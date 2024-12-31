package com.khanhisdev.movieservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
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
    private String country;
    private String description;
    // by minutes
    private int durationMin;
    @Column(name = "release_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="movie_director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private Set<Director> director;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;
    private String urlImage;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "movie_category",
            joinColumns= @JoinColumn( name = "movie_id") ,
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories= new HashSet<>();
    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimeList;
    @Column(name = "rating_point", columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal ratingPoint= BigDecimal.valueOf(0.0);
    @Column(name = "ratings")
    private Integer ratings;
    @Column(name = "price", columnDefinition="integer default 50000")
    private Integer price=50000;



}
