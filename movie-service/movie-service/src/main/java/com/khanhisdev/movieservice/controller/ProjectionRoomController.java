package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ProjectionRoomResponseDto;
import com.khanhisdev.movieservice.service.ProjectionRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectionRoom")
@Tag(name = "Projection Room Controller")
@RequiredArgsConstructor
public class ProjectionRoomController {
    private final ProjectionRoomService projectionRoomService;
    @Operation(summary = "Get all projection room projecting a movie", description = "API for get projection rooms")
    @GetMapping("/movie/{id}")
    public ResponseEntity<List<ProjectionRoomResponseDto>> getAllRoomByMovieID(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(projectionRoomService.getAllRoomByTheaterId(id), HttpStatus.OK);
    }
    @Operation(summary = "Create projection room", description = "API for create new projection room")
    @PostMapping("/theater/{id}")
    public ResponseEntity<ProjectionRoomResponseDto> createRoom(@PathVariable(name = "id") Long id
            , @RequestBody ProjectionRoomRequestDto projectionRoomRequestDto){
        return new ResponseEntity<>(projectionRoomService.createRoom(projectionRoomRequestDto,id), HttpStatus.CREATED);
    }



}
