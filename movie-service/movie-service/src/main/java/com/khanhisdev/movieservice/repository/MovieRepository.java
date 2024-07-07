package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
    List<Movie> findAllByIdIn(List<Long> ids);
}
