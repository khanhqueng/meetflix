package com.khanhisdev.userservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(name = "avatar")
    private String avatar;
    @Column(nullable = false)
    private String password;
    private boolean isActive;
    @Column(nullable = false)
    private String email;
    @Column(name = "dob")
    private LocalDate birthday;
    @Column(name = "full-name")
    private String fullName;
    @Column(name = "phone")
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private Set<Role> roles;
    @OneToMany(mappedBy = "userId", cascade =CascadeType.ALL)
    private List<LikedMovie> movieId;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
