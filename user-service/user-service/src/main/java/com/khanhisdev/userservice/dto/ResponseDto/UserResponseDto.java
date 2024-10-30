package com.khanhisdev.userservice.dto.ResponseDto;

import com.khanhisdev.userservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private Set<Role> roles;
    private List<Long> movie_ids;
    private String email;
}
