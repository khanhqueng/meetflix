package com.khanhisdev.movieservice.dto.ResponseDto;

import com.khanhisdev.movieservice.dto.RequestDto.ProjectionRoomRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterResponseDto {
    private Long id;
    private String name;
    private List<ProjectionRoomResponseDto> projectionRoomList;
}
