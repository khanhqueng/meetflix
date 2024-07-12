package com.khanhisdev.movieservice.service;

import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import com.khanhisdev.movieservice.dto.Response.ProjectionRoomResponseDto;
import com.khanhisdev.movieservice.entity.ProjectionRoom;

import java.util.List;

public interface ProjectionRoomService {
    List<ProjectionRoomResponseDto> getAllRoomByTheaterId(Long theaterId);
    ProjectionRoomResponseDto createRoom(ProjectionRoomRequestDto projectionRoomRequestDto, Long theaterId);
    ProjectionRoomResponseDto updateRoom(ProjectionRoomRequestDto projectionRoomRequestDto, Long roomId);
    void deleteRoom(Long roomId);

}