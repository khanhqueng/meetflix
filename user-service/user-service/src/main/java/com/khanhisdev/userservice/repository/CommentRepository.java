package com.khanhisdev.userservice.repository;

import com.khanhisdev.userservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMovieId(Long id);
    Boolean existsByMovieId(Long id);
    List<Comment> findByUserIdId(Long id);
}
