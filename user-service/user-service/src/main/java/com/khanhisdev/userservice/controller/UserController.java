package com.khanhisdev.userservice.controller;

import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.RequestDto.UserUpdateDto;
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

import java.util.List;

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
    @Operation(summary = "Get all users", description = "API for get all users")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
    @Operation(summary = "Get user by id", description = "API for get user by id")
    @GetMapping
    public ResponseEntity<APIResponseDto> getUserById(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.CREATED);
    }
    @Operation(summary = "Get username", description = "API for get username by userId")
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserByAdmin(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.getUserByIdAdmin(id), HttpStatus.OK) ;
    }
    @Operation(summary = "Add liked movies to user", description = "API for add liked movie to user")
    @PutMapping("/likeMovie/{id}")
    public ResponseEntity<UserResponseDto> likeMovie(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id,@PathVariable(name = "id") Long movieId){
        return new ResponseEntity<>(userService.userLikeMovie(id,movieId), HttpStatus.OK);
    }
    @Operation(summary = "Update user", description = "API for update user")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable(name = "id") Long userId, @RequestBody UserUpdateDto updateDto){
        return new ResponseEntity<>(userService.updateUser(userId,updateDto), HttpStatus.OK);
    }
    @Operation(summary = "Delete user", description = "API for delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
    }
    @GetMapping("/email/{id}")
    public ResponseEntity<String> getUserEmail(@PathVariable(name = "id") Long userId){
        return new ResponseEntity<>(userService.getEmailUser(userId), HttpStatus.OK);
    }
}
