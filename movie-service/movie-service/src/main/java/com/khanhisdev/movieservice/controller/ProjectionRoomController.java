package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ProjectionRoomResponseDto;
import com.khanhisdev.movieservice.service.ProjectionRoomService;
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
@RequestMapping("/projectionRoom")
@Tag(name = "Projection Room Controller")
@RequiredArgsConstructor
public class ProjectionRoomController {
    private final ProjectionRoomService projectionRoomService;
    @Operation(summary = "Get all projection room in a theater", description = "API for get projection rooms")
    @GetMapping("/theater/{id}")
    public ResponseEntity<List<ProjectionRoomResponseDto>> getAllRoomByTheaterId(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(projectionRoomService.getAllRoomByTheaterId(id), HttpStatus.OK);
    }
    @Operation(summary = "Create projection room", description = "API for create new projection room")
    @PostMapping("/theater/{id}")
    public ResponseEntity<ProjectionRoomResponseDto> createRoom(@PathVariable(name = "id") Long id
            , @RequestBody @Valid ProjectionRoomRequestDto projectionRoomRequestDto){
        return new ResponseEntity<>(projectionRoomService.createRoom(projectionRoomRequestDto,id), HttpStatus.CREATED);
    }
    @Operation(summary = "Update projection room ", description = "API for update projection room")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectionRoomResponseDto> updateRoom(@PathVariable(name = "id") Long id
            , @RequestBody @Valid ProjectionRoomRequestDto projectionRoomRequestDto){
        return new ResponseEntity<>(projectionRoomService.updateRoom(projectionRoomRequestDto,id), HttpStatus.OK);
    }
    @Operation(summary = "Delete projection room", description = "API for delete projection room")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable(name = "id") Long id){
        projectionRoomService.deleteRoom(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }



}
