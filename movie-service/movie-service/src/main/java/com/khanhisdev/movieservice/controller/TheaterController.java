package com.khanhisdev.movieservice.controller;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;
    @PostMapping
    public ResponseEntity<TheaterRequestDto> createTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        return new ResponseEntity<>(theaterService.createTheater(theaterRequestDto), HttpStatus.CREATED);
    }
}
