package com.khanhisdev.movieservice.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterUpdateDto {
    private String name;
    private List<ProjectionRoomUpdateDto> projectionRoomList;
}
