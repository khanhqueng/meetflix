package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeForOrderRequest;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeForOrderDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ShowtimeService {
    ShowtimeResponseDto addShowtime(ShowtimeRequestDto showtimeRequestDto);
    List<ShowtimeResponseDto> getAllShowtimeByMovieId(Long movieId);
    List<ShowtimeResponseDto> getAllShowtimeByRoomId(Long projectionRoomId);
    ShowtimeResponseDto updateShowtime(Long showtimeId, ShowtimeRequestDto showtimeRequestDto);
    List<ShowtimeForOrderDto> getShowtimeFromOrder(List<ShowtimeForOrderRequest> showtimeList);
    void deleteShowtime(Long showtimeId);
}
