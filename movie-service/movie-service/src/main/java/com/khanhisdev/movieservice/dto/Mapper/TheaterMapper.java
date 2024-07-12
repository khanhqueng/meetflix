package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.entity.Theater;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TheaterMapper {
    @Autowired
    private ModelMapper mapper;
    public Theater mapToEntity(TheaterRequestDto theaterRequestDto){
        Theater theater= mapper.map(theaterRequestDto, Theater.class);
        return theater;

    }
    public TheaterRequestDto mapToDto(Theater theater){
        TheaterRequestDto theaterRequestDto = mapper.map(theater, TheaterRequestDto.class);
        return theaterRequestDto;
    }
}
