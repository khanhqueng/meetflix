package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;

import java.util.List;

public interface TheaterService {
    List<TheaterRequestDto> getAllTheaters();
    TheaterRequestDto getTheaterById(Long id);
    TheaterRequestDto createTheater(TheaterRequestDto theaterRequestDto);
    TheaterRequestDto updateTheater(TheaterRequestDto theaterRequestDto, Long id);
    void deleteTheater(Long id);
}
