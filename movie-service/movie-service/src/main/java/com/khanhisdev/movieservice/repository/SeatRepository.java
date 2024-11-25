package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<List<Seat>> findByProjectionRoomId(Long roomId);
}
