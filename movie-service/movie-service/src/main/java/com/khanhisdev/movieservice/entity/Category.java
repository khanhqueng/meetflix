package com.khanhisdev.movieservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String thumbnailUrl;
    private String url_key;
    @ManyToMany(mappedBy ="categories")
    private Set<Movie> movies;
}
