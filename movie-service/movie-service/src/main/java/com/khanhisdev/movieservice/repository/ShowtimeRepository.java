package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Showtime;
import org.springframework.boot.actuate.endpoint.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {
    List<Showtime> findByMovieId(Long id);
    List<Showtime> findByProjectionRoomId(Long id);
    Boolean existsByStartTimeBetweenAndTheaterIdAndProjectionRoomId(LocalDateTime start,
                                                                    LocalDateTime end,
                                                                    Long theaterId,
                                                                    Long projectionRoomId);
    Optional<Showtime> findByStartTimeAndProjectionRoomId(LocalDateTime startTime, Long id);
}
