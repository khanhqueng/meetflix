package com.khanhisdev.userservice.service;

import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.ResponseDto.APIResponseDto;
import com.khanhisdev.userservice.dto.ResponseDto.UserResponseDto;
import com.khanhisdev.userservice.entity.LikedMovie;

public interface UserService {
    UserDto createUser(UserDto userDto);
    APIResponseDto getUserById(Long id);
    UserDto getUserByUsername(String userName);
    UserResponseDto userLikeMovie(Long userId, Long movieId);
}
