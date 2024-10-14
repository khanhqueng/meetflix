package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long> {
    Boolean existsByName(String name);
}
