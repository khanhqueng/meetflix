package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long> {
    Boolean existsByName(String name);
    @Query("SELECT t FROM Theater t JOIN t.showtimeList s JOIN s.movie m " +
            "WHERE DATE(s.startTime) = :date " +
            "AND m.id = :movieId")
    Optional<List<Theater>> findByDateAndMovieId(@Param("date") LocalDate date, @Param("movieId") Long movieId);
}
