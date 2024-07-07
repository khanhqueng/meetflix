package com.khanhisdev.userservice.dto.Response;

import com.khanhisdev.userservice.dto.Model.MovieDto;
import com.khanhisdev.userservice.dto.Model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {
    private UserDto userDto;
    private List<MovieDto> movieDto;
}
