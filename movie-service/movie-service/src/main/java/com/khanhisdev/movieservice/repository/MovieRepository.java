package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
    List<Movie> findByShowtimeListTheaterId(Long theaterId);
    List<Movie> findAllByIdIn(List<Long> ids);
    // No need Native query
    @Query(
            value = "SELECT DISTINCT m FROM Movie m " +
                    "JOIN m.actors a " +
                    "JOIN m.director d " +
                    "WHERE m.name LIKE %:searchText% " +
                    "OR a.name LIKE %:searchText% " +
                    "OR d.name LIKE %:searchText% " +
                    "ORDER BY m.id ASC"
    )
    Page<Movie> findMoviesBySearchText(@Param("searchText") String searchText, Pageable pageable);
}
