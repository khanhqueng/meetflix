package com.khanhisdev.userservice.controller;

import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.ResponseDto.APIResponseDto;
import com.khanhisdev.userservice.dto.ResponseDto.UserResponseDto;
import com.khanhisdev.userservice.entity.LikedMovie;
import com.khanhisdev.userservice.service.UserService;
import com.khanhisdev.userservice.utils.CustomHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<APIResponseDto> getUserById(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.CREATED);
    }
    @PutMapping("/likeMovie/{id}")
    public ResponseEntity<UserResponseDto> likeMovie(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id,@PathVariable(name = "id") Long movieId){
        return new ResponseEntity<>(userService.userLikeMovie(id,movieId), HttpStatus.OK);
    }
}
