package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {
    List<Showtime> findByDateAndProjectionRoomId(LocalDate date, Long id);
    List<Showtime> findByMovieId(Long id);
    List<Showtime> findByProjectionRoomId(Long id);
    Optional<Showtime> findByTimeAndProjectionRoomId(String time, Long RoomId);
}
