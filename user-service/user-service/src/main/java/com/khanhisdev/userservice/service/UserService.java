package com.khanhisdev.userservice.service;

import com.khanhisdev.userservice.dto.Model.UserDto;
import com.khanhisdev.userservice.dto.Response.APIResponseDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    APIResponseDto getUserById(Long id);
    UserDto getUserByUsername(String userName);
}
