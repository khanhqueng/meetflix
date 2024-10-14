package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
@Tag(name = "Theater Controller")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;
    @Operation(summary = "Add new theater", description = "API for create new theater")
    @PostMapping
    public ResponseEntity<TheaterRequestDto> createTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return new ResponseEntity<>(theaterService.createTheater(theaterRequestDto), HttpStatus.CREATED);
    }
}
