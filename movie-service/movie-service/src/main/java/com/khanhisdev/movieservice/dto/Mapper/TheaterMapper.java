package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.MovieDto;
import com.khanhisdev.movieservice.dto.RequestDto.TheaterDto;
import com.khanhisdev.movieservice.entity.Movie;
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
    public Theater mapToEntity(TheaterDto theaterDto){
        Theater theater= mapper.map(theaterDto, Theater.class);
        return theater;

    }
    public TheaterDto mapToDto(Theater theater){
        TheaterDto theaterDto= mapper.map(theater, TheaterDto.class);
        return theaterDto;
    }
}
