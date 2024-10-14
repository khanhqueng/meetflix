package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByName(String name);
    Boolean existsByName(String name);
}
