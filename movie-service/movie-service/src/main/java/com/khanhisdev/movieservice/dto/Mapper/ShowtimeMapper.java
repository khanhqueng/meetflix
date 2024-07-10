package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.MovieDto;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeDto;
import com.khanhisdev.movieservice.dto.Response.ShowtimeResponseDto;
import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.entity.Showtime;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ShowtimeMapper {
    @Autowired
    private ModelMapper mapper;
    public Showtime mapToEntity(ShowtimeDto showtimeDto){
        Showtime showtime= mapper.map(showtimeDto, Showtime.class);
        return showtime;

    }
    public ShowtimeDto mapToDto(Showtime showtime){
        ShowtimeDto showtimeDto= mapper.map(showtime, ShowtimeDto.class);
        return showtimeDto;
    }
    public ShowtimeResponseDto mapToResponseDto(Showtime showtime){
        ShowtimeResponseDto showtimeResponseDto= mapper.map(showtime, ShowtimeResponseDto.class);
        return showtimeResponseDto;
    }
}
