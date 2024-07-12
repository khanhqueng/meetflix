package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.ProjectionRoom;
import com.khanhisdev.movieservice.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProjectionRoomRepository extends JpaRepository<ProjectionRoom,Long> {
    List<ProjectionRoom> findByTheaterId(Long theaterId);
}
