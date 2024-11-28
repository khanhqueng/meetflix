package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterUpdateDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheaterResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheatersAndShowTimesResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface TheaterService {
    List<TheaterResponseDto> getAllTheaters();
    TheaterResponseDto getTheaterById(Long id);
    TheaterResponseDto createTheater(TheaterRequestDto theaterRequestDto);
    TheaterResponseDto updateTheater(TheaterUpdateDto theaterUpdateDto, Long id);
    List<TheatersAndShowTimesResponseDto> getByMovieIdAndDate(LocalDate date, Long movieId);
    void deleteTheater(Long id);
}
