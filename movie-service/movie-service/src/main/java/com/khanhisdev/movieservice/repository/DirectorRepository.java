package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Actor;
import com.khanhisdev.movieservice.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository< Director, Long> {
    Optional<Director> findByName(String name);
}
