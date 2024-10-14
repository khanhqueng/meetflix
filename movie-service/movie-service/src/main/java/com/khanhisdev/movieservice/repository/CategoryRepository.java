package com.khanhisdev.movieservice.repository;

import com.khanhisdev.movieservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Boolean existsByName(String name);
}
