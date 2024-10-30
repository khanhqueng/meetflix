package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ProjectionRoomResponseDto;
import com.khanhisdev.movieservice.entity.ProjectionRoom;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
public interface ProjectionRoomMapper extends GenericMapper<ProjectionRoom,ProjectionRoomRequestDto,ProjectionRoomResponseDto> {

}
