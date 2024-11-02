package com.khanhisdev.userservice.dto.RequestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.khanhisdev.userservice.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    @Email
    private String email;
    @NotNull(message = "birthday must not be null")
    @JsonFormat
    private LocalDate birthday;
    @NotNull(message = "name must not be null")
    private String fullName;
    @NotNull(message = "phone number must not be null")
    private String phoneNumber;
    @NotNull(message = "role must not be null")
    private Set<Role> roles;
}
