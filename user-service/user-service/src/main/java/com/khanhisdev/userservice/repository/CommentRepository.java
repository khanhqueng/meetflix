package com.khanhisdev.userservice.repository;

import com.khanhisdev.userservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMovieId(Long id);
    Boolean existsByMovieId(Long id);
    List<Comment> findByUserIdId(Long id);
}
