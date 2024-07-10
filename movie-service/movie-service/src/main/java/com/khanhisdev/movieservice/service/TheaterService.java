package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterDto;

import java.util.List;

public interface TheaterService {
    List<TheaterDto> getAllTheaters();
    TheaterDto getTheaterById(Long id);
    TheaterDto createTheater(TheaterDto theaterDto);
    TheaterDto updateTheater(TheaterDto theaterDto, Long id);
    void deleteTheater(Long id);
}
