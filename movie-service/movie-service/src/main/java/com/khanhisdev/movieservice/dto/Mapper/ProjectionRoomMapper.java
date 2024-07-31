package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ProjectionRoomResponseDto;
import com.khanhisdev.movieservice.entity.ProjectionRoom;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ProjectionRoomMapper {
    @Autowired
    private ModelMapper mapper;
    public ProjectionRoom mapToEntity(ProjectionRoomRequestDto projectionRoomRequestDto){
        ProjectionRoom projectionRoom= mapper.map(projectionRoomRequestDto, ProjectionRoom.class);
        return projectionRoom;

    }
    public ProjectionRoomRequestDto mapToDto(ProjectionRoom projectionRoom){
        ProjectionRoomRequestDto projectionRoomRequestDto = mapper.map(projectionRoom, ProjectionRoomRequestDto.class);
        return projectionRoomRequestDto;
    }
    public ProjectionRoomResponseDto mapToResponseDto(ProjectionRoom projectionRoom){
        ProjectionRoomResponseDto projectionRoomResponseDto = mapper.map(projectionRoom, ProjectionRoomResponseDto.class);
        return projectionRoomResponseDto;
    }
}
