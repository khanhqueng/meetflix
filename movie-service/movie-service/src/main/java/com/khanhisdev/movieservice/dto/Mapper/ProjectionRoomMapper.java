package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.MovieDto;
import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomDto;
import com.khanhisdev.movieservice.entity.Movie;
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
    public ProjectionRoom mapToEntity(ProjectionRoomDto projectionRoomDto){
        ProjectionRoom projectionRoom= mapper.map(projectionRoomDto, ProjectionRoom.class);
        return projectionRoom;

    }
    public ProjectionRoomDto mapToDto(ProjectionRoom projectionRoom){
        ProjectionRoomDto projectionRoomDto= mapper.map(projectionRoom, ProjectionRoomDto.class);
        return projectionRoomDto;
    }
}
