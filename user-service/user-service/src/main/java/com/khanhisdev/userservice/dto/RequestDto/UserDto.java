package com.khanhisdev.userservice.dto.RequestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khanhisdev.userservice.entity.Comment;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    @NotNull(message = "Username cannot be empty")
    private String username;
    @NotNull(message = "Password cannot be empty")
    private String password;
    @NotNull(message = "Email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "birthday must not be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    @NotNull(message = "name must not be null")
    private String fullName;
    @NotNull(message = "phone number must not be null")
    private String phoneNumber;
    @JsonProperty
    private boolean active;
    private List<Long> movies_id;
    private List<CommentDto> comments;
}
