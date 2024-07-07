package com.khanhisdev.userservice.repository;

import com.khanhisdev.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
