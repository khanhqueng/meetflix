package com.khanhisdev.movieservice.service.impl;

import com.khanhisdev.movieservice.dto.Mapper.ProjectionRoomMapper;
import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import com.khanhisdev.movieservice.dto.Response.ProjectionRoomResponseDto;
import com.khanhisdev.movieservice.entity.ProjectionRoom;
import com.khanhisdev.movieservice.entity.Theater;
import com.khanhisdev.movieservice.exception.ResourceNotFoundException;
import com.khanhisdev.movieservice.repository.ProjectionRoomRepository;
import com.khanhisdev.movieservice.repository.TheaterRepository;
import com.khanhisdev.movieservice.service.ProjectionRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectionRoomServiceImpl implements ProjectionRoomService {
    private ProjectionRoomRepository projectionRoomRepository;
    private TheaterRepository theaterRepository;
    private ProjectionRoomMapper mapper;
    @Override
    public List<ProjectionRoomResponseDto> getAllRoomByTheaterId(Long theaterId) {
        if(!theaterRepository.existsById(theaterId))
            throw new ResourceNotFoundException("Theater","id", theaterId);
        return projectionRoomRepository.findByTheaterId(theaterId).stream().map(projectionRoom -> mapper.mapToResponseDto(projectionRoom)).toList();
    }

    @Override
    public ProjectionRoomResponseDto createRoom(ProjectionRoomRequestDto projectionRoomRequestDto, Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(()-> new ResourceNotFoundException("Theater","id", theaterId));
        ProjectionRoom projectionRoom= mapper.mapToEntity(projectionRoomRequestDto);
        projectionRoom.setTheater(theater);
        return mapper.mapToResponseDto(projectionRoomRepository.save(projectionRoom));
    }

    @Override
    public ProjectionRoomResponseDto updateRoom(ProjectionRoomRequestDto projectionRoomRequestDto, Long roomId) {
        ProjectionRoom projectionRoom = projectionRoomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room","id", roomId));
        projectionRoom.setNumber(projectionRoomRequestDto.getNumber());
        projectionRoom.setSeats(projectionRoomRequestDto.getSeats());
        return mapper.mapToResponseDto(projectionRoomRepository.save(projectionRoom));
    }

    @Override
    public void deleteRoom(Long roomId) {
        ProjectionRoom projectionRoom = projectionRoomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room","id", roomId));
        projectionRoomRepository.delete(projectionRoom);
    }
}
