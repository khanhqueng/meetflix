package com.khanhisdev.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false,referencedColumnName = "user_id")
    private User userId;
    @Column(name = "movie_id")
    private Long movieId;
}
