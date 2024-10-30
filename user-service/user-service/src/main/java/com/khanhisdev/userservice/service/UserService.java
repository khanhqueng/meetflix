package com.khanhisdev.userservice.service;

import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.ResponseDto.APIResponseDto;
import com.khanhisdev.userservice.dto.ResponseDto.UserResponseDto;
import com.khanhisdev.userservice.entity.LikedMovie;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserResponseDto> getAllUser();
    APIResponseDto getUserById(Long id);
    UserDto getUserByUsername(String userName);
    UserResponseDto userLikeMovie(Long userId, Long movieId);
    String getEmailByUserId(Long id);
}
