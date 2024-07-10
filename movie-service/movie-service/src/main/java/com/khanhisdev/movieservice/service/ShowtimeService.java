package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeDto;
import com.khanhisdev.movieservice.dto.Response.ShowtimeResponseDto;

import java.util.List;

public interface ShowtimeService {
    ShowtimeResponseDto addShowtime(ShowtimeDto showtimeDto, Long movieId, Long theaterId, Long projectionRoomId);
    List<ShowtimeResponseDto> getAllShowtimeByMovieId(Long movieId);
    List<ShowtimeResponseDto> getAllShowtimeByRoomId(Long projectionRoomId);
    ShowtimeResponseDto updateShowtime(Long showtimeId, ShowtimeDto showtimeDto);
    void deleteShowtime(Long showtimeId);
}
