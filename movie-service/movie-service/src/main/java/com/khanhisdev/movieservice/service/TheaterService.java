package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheaterResponseDto;

import java.util.List;

public interface TheaterService {
    List<TheaterResponseDto> getAllTheaters();
    TheaterResponseDto getTheaterById(Long id);
    TheaterResponseDto createTheater(TheaterRequestDto theaterRequestDto);
    TheaterResponseDto updateTheater(TheaterRequestDto theaterRequestDto, Long id);
    void deleteTheater(Long id);
}
