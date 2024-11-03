package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.commons.dto.ShowtimeForOrderDto;
import com.khanhisdev.movieservice.dto.Mapper.ShowtimeMapper;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeForOrderRequest;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.entity.ProjectionRoom;
import com.khanhisdev.movieservice.entity.Showtime;
import com.khanhisdev.movieservice.entity.Theater;
import com.khanhisdev.movieservice.exception.MovieAPIException;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.MovieRepository;
import com.khanhisdev.movieservice.repository.ProjectionRoomRepository;
import com.khanhisdev.movieservice.repository.ShowtimeRepository;
import com.khanhisdev.movieservice.repository.TheaterRepository;
import com.khanhisdev.movieservice.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ShowtimeServiceImpl implements ShowtimeService {
    private ShowtimeRepository showtimeRepository;
    private ProjectionRoomRepository projectionRoomRepository;
    private MovieRepository movieRepository;
    private TheaterRepository theaterRepository;
    private ShowtimeMapper mapper;
    @Override
    public ShowtimeResponseDto addShowtime(ShowtimeRequestDto showtimeRequestDto) {
        Movie movie = movieRepository.findById(showtimeRequestDto.getMovie_id()).orElseThrow(
                ()-> new ResourceNotFoundException("Movie","id",showtimeRequestDto.getMovie_id()));
        Theater theater = theaterRepository.findById(showtimeRequestDto.getTheater_id()).orElseThrow(
                ()-> new ResourceNotFoundException("Theater","id",showtimeRequestDto.getTheater_id()));
        ProjectionRoom projectionRoom = projectionRoomRepository.findByTheaterIdAndId(showtimeRequestDto.getTheater_id(), showtimeRequestDto.getProjectionRoom_id()).orElseThrow(
                ()-> new ResourceNotFoundException("ProjectionRoom with theater","id", showtimeRequestDto.getProjectionRoom_id()));

        if(showtimeRepository.existsByStartTimeBetweenAndTheaterIdAndProjectionRoomId(
                showtimeRequestDto.getStartTime().minus(Duration.ofMinutes(movie.getDurationMin())),
                showtimeRequestDto.getStartTime().plus(Duration.ofMinutes(movie.getDurationMin())),
                theater.getId(),
                projectionRoom.getId()
        ) )
            throw new MovieAPIException(HttpStatus.BAD_REQUEST,"Invalid Showtime");
        Showtime showtime= mapper.mapToEntity(showtimeRequestDto);
        showtime.setMovie(movie);
        showtime.setProjectionRoom(projectionRoom);
        showtime.setTheater(theater);
        return mapper.mapToDto(showtimeRepository.save(showtime));
    }

    @Override
    public List<ShowtimeResponseDto> getAllShowtimeByMovieId(Long movieId) {
        if(!movieRepository.existsById(movieId)){
            throw new ResourceNotFoundException("Movie","id",movieId);
        }
        return showtimeRepository.findByMovieId(movieId).stream()
                .map(showtime -> mapper.mapToDto(showtime)).toList();
    }

    @Override
    public List<ShowtimeResponseDto> getAllShowtimeByRoomId(Long projectionRoomId) {
        if(!projectionRoomRepository.existsById(projectionRoomId)){
            throw new ResourceNotFoundException("ProjectionRoom","id",projectionRoomId);
        }
        return showtimeRepository.findByProjectionRoomId(projectionRoomId).stream()
                .map(showtime -> mapper.mapToDto(showtime)).toList();
    }

    @Override
    public ShowtimeResponseDto updateShowtime(Long showtimeId, ShowtimeRequestDto showtimeRequestDto) {
        Showtime showtime= showtimeRepository.findById(showtimeId).orElseThrow(()-> new ResourceNotFoundException("Showtime", "id", showtimeId));
        Movie movie= showtime.getMovie();
        if(showtimeRepository.existsByStartTimeBetweenAndTheaterIdAndProjectionRoomId(
                showtimeRequestDto.getStartTime().minus(Duration.ofMinutes(movie.getDurationMin())),
                showtimeRequestDto.getStartTime().plus(Duration.ofMinutes(movie.getDurationMin())),
                showtime.getTheater().getId(),
                showtime.getProjectionRoom().getId()
        ))
            throw new MovieAPIException(HttpStatus.BAD_REQUEST,"Invalid Showtime");
        showtime.setStartTime(showtimeRequestDto.getStartTime());
        return mapper.mapToDto(showtimeRepository.save(showtime));
    }

    @Override
    public List<ShowtimeForOrderDto> getShowtimeFromOrder(List<ShowtimeForOrderRequest> showtimeList) {
        List<ShowtimeForOrderDto> response= new ArrayList<>();
        for(ShowtimeForOrderRequest request: showtimeList){
            Showtime showtime= showtimeRepository.findByStartTimeAndProjectionRoomId(request.getTime(), request.getRoomId()).orElseThrow(
                    ()-> new ResourceNotFoundException("Showtime", "Time or RoomId", request.getRoomId())
            );
            ShowtimeForOrderDto showtimeForOrderDto= mapper.mapToResponseOrderDto(showtime);
            showtimeForOrderDto.setSeats(request.getSeatsOrdered());
            response.add(showtimeForOrderDto);
        }

        return response;
    }

    @Override
    public void deleteShowtime(Long showtimeId) {
        Showtime showtime= showtimeRepository.findById(showtimeId).orElseThrow(()-> new ResourceNotFoundException("Showtime", "id", showtimeId));
        showtimeRepository.delete(showtime);

    }
}
