package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.ShowtimeMapper;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeForOrderRequest;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeForOrderDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {
    private ShowtimeRepository showtimeRepository;
    private ProjectionRoomRepository projectionRoomRepository;
    private MovieRepository movieRepository;
    private TheaterRepository theaterRepository;
    private ShowtimeMapper mapper;
    @Override
    public ShowtimeResponseDto addShowtime(ShowtimeRequestDto showtimeRequestDto, Long movieId, Long theaterId, Long projectionRoomId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new ResourceNotFoundException("Movie","id",movieId));
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(()-> new ResourceNotFoundException("Theater","id",theaterId));
        ProjectionRoom projectionRoom = projectionRoomRepository.findByTheaterIdAndId(theaterId,projectionRoomId).orElseThrow(()-> new ResourceNotFoundException("ProjectionRoom with theater","id",projectionRoomId));
        List<Showtime> showtime= showtimeRepository.findByDateAndProjectionRoomId(showtimeRequestDto.getDate(), projectionRoomId);
        int hourOfMovie= movie.getDurationMin()/60;
        int minutesOfMovie= movie.getDurationMin()%60;
        for(Showtime time: showtime){
           String[] parts= time.getTime().split(":");
           int hours= Integer.parseInt(parts[0]);
           int minutes= Integer.parseInt(parts[1]);
           int floorTimeHours= hours+ hourOfMovie;
           int floorTimeMinutes= minutes+minutesOfMovie;
           if(floorTimeMinutes>=60){
               floorTimeMinutes-=60;
               floorTimeHours+=1;
           }
            int flatTimeHours= hours- hourOfMovie;
            int flatTimeMinutes= minutes-minutesOfMovie;
            if(flatTimeMinutes<=0){
                flatTimeMinutes+=60;
                flatTimeHours-=1;
            }
            String[] inputParts= showtimeRequestDto.getTime().split(":");
            int inputHours= Integer.parseInt(inputParts[0]);
            int inputMinutes= Integer.parseInt(inputParts[1]);
           if(flatTimeHours< inputHours && inputHours<floorTimeHours){
               throw new MovieAPIException(HttpStatus.BAD_REQUEST, "Time in an invalid range");
           }
           if(flatTimeHours==inputHours){
               if(flatTimeMinutes<=inputMinutes) throw new MovieAPIException(HttpStatus.BAD_REQUEST, "Time in an invalid range");
           }
           if(floorTimeHours==inputHours){
                if(floorTimeMinutes>=inputMinutes) throw new MovieAPIException(HttpStatus.BAD_REQUEST, "Time in an invalid range");
            }

        }
        Showtime newShowtime= mapper.mapToEntity(showtimeRequestDto);
        newShowtime.setTheater(theater);
        newShowtime.setMovie(movie);
        newShowtime.setProjectionRoom(projectionRoom);
        return mapper.mapToResponseDto(showtimeRepository.save(newShowtime));
    }

    @Override
    public List<ShowtimeResponseDto> getAllShowtimeByMovieId(Long movieId) {
        if(!movieRepository.existsById(movieId)){
            throw new ResourceNotFoundException("Movie","id",movieId);
        }
        return showtimeRepository.findByMovieId(movieId).stream()
                .map(showtime -> mapper.mapToResponseDto(showtime)).toList();
    }

    @Override
    public List<ShowtimeResponseDto> getAllShowtimeByRoomId(Long projectionRoomId) {
        if(!projectionRoomRepository.existsById(projectionRoomId)){
            throw new ResourceNotFoundException("ProjectionRoom","id",projectionRoomId);
        }
        return showtimeRepository.findByProjectionRoomId(projectionRoomId).stream()
                .map(showtime -> mapper.mapToResponseDto(showtime)).toList();
    }

    @Override
    public ShowtimeResponseDto updateShowtime(Long showtimeId, ShowtimeRequestDto showtimeRequestDto) {
        Showtime showtime= showtimeRepository.findById(showtimeId).orElseThrow(()-> new ResourceNotFoundException("Showtime", "id", showtimeId));
        Movie movie= showtime.getMovie();

        List<Showtime> showtimeList= showtimeRepository.findByDateAndProjectionRoomId(showtimeRequestDto.getDate(), showtime.getProjectionRoom().getId());
        int hourOfMovie= movie.getDurationMin()/60;
        int minutesOfMovie= movie.getDurationMin()%60;
        for(Showtime time: showtimeList){
            if(Objects.equals(time.getId(), showtimeId)) continue;
            String[] parts= time.getTime().split(":");
            int hours= Integer.parseInt(parts[0]);
            int minutes= Integer.parseInt(parts[1]);
            int floorTimeHours= hours+ hourOfMovie;
            int floorTimeMinutes= minutes+minutesOfMovie;
            if(floorTimeMinutes>=60){
                floorTimeMinutes-=60;
                floorTimeHours+=1;
            }
            int flatTimeHours= hours- hourOfMovie;
            int flatTimeMinutes= minutes-minutesOfMovie;
            if(flatTimeMinutes<=0){
                flatTimeMinutes+=60;
                flatTimeHours-=1;
            }
            String[] inputParts= showtimeRequestDto.getTime().split(":");
            int inputHours= Integer.parseInt(inputParts[0]);
            int inputMinutes= Integer.parseInt(inputParts[1]);
            if(flatTimeHours< inputHours && inputHours<floorTimeHours){
                throw new MovieAPIException(HttpStatus.BAD_REQUEST, "Time in an invalid range");
            }
            if(flatTimeHours==inputHours){
                if(flatTimeMinutes<=inputMinutes) throw new MovieAPIException(HttpStatus.BAD_REQUEST, "Time in an invalid range");
            }
            if(floorTimeHours==inputHours){
                if(floorTimeMinutes>=inputMinutes) throw new MovieAPIException(HttpStatus.BAD_REQUEST, "Time in an invalid range");
            }

        }
        showtime.setTime(showtimeRequestDto.getTime());
        showtime.setDate(showtimeRequestDto.getDate());
        return mapper.mapToResponseDto(showtimeRepository.save(showtime));
    }

    @Override
    public List<ShowtimeForOrderDto> getShowtimeFromOrder(List<ShowtimeForOrderRequest> showtimeList) {
        List<ShowtimeForOrderDto> response= new ArrayList<>();
        for(ShowtimeForOrderRequest request: showtimeList){
            Showtime showtime= showtimeRepository.findByTimeAndProjectionRoomId(request.getTime(), request.getRoomId()).orElseThrow(
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
