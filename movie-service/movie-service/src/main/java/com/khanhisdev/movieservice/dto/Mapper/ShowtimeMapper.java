package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.Response.ShowtimeResponseDto;
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
    public Showtime mapToEntity(ShowtimeRequestDto showtimeRequestDto){
        Showtime showtime= mapper.map(showtimeRequestDto, Showtime.class);
        return showtime;

    }
    public ShowtimeRequestDto mapToDto(Showtime showtime){
        ShowtimeRequestDto showtimeRequestDto = mapper.map(showtime, ShowtimeRequestDto.class);
        return showtimeRequestDto;
    }
    public ShowtimeResponseDto mapToResponseDto(Showtime showtime){
        ShowtimeResponseDto showtimeResponseDto= mapper.map(showtime, ShowtimeResponseDto.class);
        return showtimeResponseDto;
    }
}
