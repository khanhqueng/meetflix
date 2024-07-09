package com.khanhisdev.userservice.dto.ResponseDto;

import com.khanhisdev.userservice.dto.RequestDto.MovieDto;
import com.khanhisdev.userservice.dto.RequestDto.UserDto;
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
