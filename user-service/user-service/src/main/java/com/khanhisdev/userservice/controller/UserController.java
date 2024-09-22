package com.khanhisdev.userservice.controller;

import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.ResponseDto.APIResponseDto;
import com.khanhisdev.userservice.dto.ResponseDto.UserResponseDto;
import com.khanhisdev.userservice.entity.LikedMovie;
import com.khanhisdev.userservice.service.UserService;
import com.khanhisdev.userservice.utils.CustomHeaders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User-controller")
public class UserController {

    private final UserService userService;
    @Operation(summary = "Create new user", description = "API for create new user")
    @PostMapping
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Get user by id", description = "API for get user by id")
    @GetMapping
    public ResponseEntity<APIResponseDto> getUserById(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.CREATED);
    }
    @Operation(summary = "Get username", description = "API for get username by userId")
    @GetMapping("{id}")
    public ResponseEntity<String> getUsername(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getEmailByUserId(id), HttpStatus.OK) ;
    }
    @Operation(summary = "Add liked movies to user", description = "API for add liked movie to user")
    @PutMapping("/likeMovie/{id}")
    public ResponseEntity<UserResponseDto> likeMovie(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id,@PathVariable(name = "id") Long movieId){
        return new ResponseEntity<>(userService.userLikeMovie(id,movieId), HttpStatus.OK);
    }
}
