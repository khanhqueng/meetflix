package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ProjectionRoomResponseDto;
import com.khanhisdev.movieservice.service.ProjectionRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectionRoom")
public class ProjectionRoomController {
    @Autowired
    private ProjectionRoomService projectionRoomService;
    @GetMapping("/movie/{id}")
    public ResponseEntity<List<ProjectionRoomResponseDto>> getAllRoomByMovieID(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(projectionRoomService.getAllRoomByTheaterId(id), HttpStatus.OK);
    }
    @PostMapping("/theater/{id}")
    public ResponseEntity<ProjectionRoomResponseDto> createRoom(@PathVariable(name = "id") Long id
            , @RequestBody ProjectionRoomRequestDto projectionRoomRequestDto){
        return new ResponseEntity<>(projectionRoomService.createRoom(projectionRoomRequestDto,id), HttpStatus.CREATED);
    }



}
