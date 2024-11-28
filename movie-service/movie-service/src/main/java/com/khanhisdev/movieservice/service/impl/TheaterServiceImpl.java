package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.ShowtimeMapper;
import com.khanhisdev.movieservice.dto.Mapper.TheaterMapper;
import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomUpdateDto;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterUpdateDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheaterResponseDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheatersAndShowTimesResponseDto;
import com.khanhisdev.movieservice.entity.ProjectionRoom;
import com.khanhisdev.movieservice.entity.Seat;
import com.khanhisdev.movieservice.entity.Showtime;
import com.khanhisdev.movieservice.entity.Theater;
import com.khanhisdev.movieservice.exception.ResourceDuplicateException;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.ProjectionRoomRepository;
import com.khanhisdev.movieservice.repository.TheaterRepository;
import com.khanhisdev.movieservice.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class TheaterServiceImpl implements TheaterService {
    private TheaterRepository theaterRepository;
    private TheaterMapper theaterMapper;
    private ShowtimeMapper showtimeMapper;
    private ProjectionRoomRepository projectionRoomRepository;
    @Override
    public List<TheaterResponseDto> getAllTheaters() {
        return theaterRepository.findAll().stream().map(theater -> theaterMapper.mapToDto(theater)).toList();
    }

    @Override
    public TheaterResponseDto getTheaterById(Long id) {

        return theaterMapper.mapToDto(theaterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Theater","id",id)));
    }

    @Override
    public TheaterResponseDto createTheater(TheaterRequestDto theaterRequestDto) {
        if(theaterRepository.existsByName(theaterRequestDto.getName())){
            throw new ResourceDuplicateException("Theater", "name", theaterRequestDto.getName());
        }
        Theater theater= theaterMapper.mapToEntity(theaterRequestDto);
        for(ProjectionRoom projectionRoom : theater.getProjectionRoomList()){
            int remainder = projectionRoom.getSeats()%14;
            int row= projectionRoom.getSeats()/14+1;
            List<Seat> seats = new ArrayList<>();
            for(int i=1;i<=14;i++){
                for(int j=1;j<row;j++){
                    String seatName=i + String.valueOf((char) ('A'+ j-1));
                    Seat newSeat= Seat.builder()
                            .name(seatName)
                            .projectionRoom(projectionRoom)
                            .build();
                    seats.add(newSeat);
                }
            }
            for(int i=1;i<=remainder;i++) {
                String seatName=i + String.valueOf((char) ('A'+ row-1));
                Seat newSeat = Seat.builder()
                        .name(seatName)
                        .projectionRoom(projectionRoom)
                        .build();
                seats.add(newSeat);
            }
            projectionRoom.setSeat(seats);
        }
        for (ProjectionRoom projectionRoom: theater.getProjectionRoomList()){
            projectionRoom.setTheater(theater);
        }
        return theaterMapper.mapToDto(theaterRepository.save(theater));
    }

    @Override
    public TheaterResponseDto updateTheater(TheaterUpdateDto theaterUpdateDto, Long id) {
        Theater theater= theaterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Theater", "id", id));
        List<ProjectionRoom> currentRoom = theater.getProjectionRoomList();
        List<ProjectionRoomUpdateDto> updatedRooms= theaterUpdateDto.getProjectionRoomList();
        for(ProjectionRoomUpdateDto updateDto : updatedRooms){
            ProjectionRoom projectionRoom= projectionRoomRepository.findById(updateDto.getId()).orElseThrow(
                    ()-> new ResourceNotFoundException("Projection Room", "id", updateDto.getId())
            );
            currentRoom.remove(projectionRoom);
            projectionRoom.setNumber(updateDto.getNumber());
            projectionRoom.setSeats(updateDto.getSeats());
            currentRoom.add(projectionRoom);
        }
        theater.setProjectionRoomList(currentRoom);
        theater.setName(theaterUpdateDto.getName());
        return theaterMapper.mapToDto(theaterRepository.save(theater));
    }

    @Override
    public List<TheatersAndShowTimesResponseDto> getByMovieIdAndDate(LocalDate date, Long movieId) {
        List<Theater> theaters = theaterRepository.findByDateAndMovieId(date,movieId).orElseThrow(
                ()->new ResourceNotFoundException("Theater", "date or movieId", date.toString()+" "+movieId )
        );
        List<TheatersAndShowTimesResponseDto> response= new ArrayList<>();
        for(Theater theater: theaters){
            TheatersAndShowTimesResponseDto theaterWithShowTimes= theaterMapper.mapToTheaterWithShowtime(theater);
            List<ShowtimeResponseDto> showTimes= theater.getShowtimeList().stream()
                    .filter(showtime -> Objects.equals(showtime.getMovie().getId(), movieId)
                            && showtime.getStartTime().toLocalDate().equals(date))
                    .map(showtime -> showtimeMapper.mapToDto(showtime))
                    .toList();
            theaterWithShowTimes.setShowTimes(showTimes);
            response.add(theaterWithShowTimes);
        }
        return response;
    }

    @Override
    public void  deleteTheater(Long id) {
        Theater theater= theaterRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Theater", "id", id));
        theaterRepository.delete(theater);
    }
}
