package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.Response.ShowtimeResponseDto;

import java.util.List;

public interface ShowtimeService {
    ShowtimeResponseDto addShowtime(ShowtimeRequestDto showtimeRequestDto, Long movieId, Long theaterId, Long projectionRoomId);
    List<ShowtimeResponseDto> getAllShowtimeByMovieId(Long movieId);
    List<ShowtimeResponseDto> getAllShowtimeByRoomId(Long projectionRoomId);
    ShowtimeResponseDto updateShowtime(Long showtimeId, ShowtimeRequestDto showtimeRequestDto);
    void deleteShowtime(Long showtimeId);
}
