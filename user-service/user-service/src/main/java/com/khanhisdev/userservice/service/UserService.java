package com.khanhisdev.userservice.service;

import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.ResponseDto.APIResponseDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    APIResponseDto getUserById(Long id);
    UserDto getUserByUsername(String userName);
}
