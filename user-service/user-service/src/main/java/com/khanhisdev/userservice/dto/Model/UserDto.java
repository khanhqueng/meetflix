package com.khanhisdev.userservice.dto.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    @JsonProperty
    private boolean active;
    private String movieId;
}
