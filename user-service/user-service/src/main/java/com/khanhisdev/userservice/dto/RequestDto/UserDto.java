package com.khanhisdev.userservice.dto.RequestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
