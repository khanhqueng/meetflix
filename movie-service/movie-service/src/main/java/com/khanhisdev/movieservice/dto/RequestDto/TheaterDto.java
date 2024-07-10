package com.khanhisdev.movieservice.dto.RequestDto;

import com.khanhisdev.movieservice.entity.ProjectionRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {
    private String name;
    private List<ProjectionRoomDto> projectionRoomList;
}
