package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeForOrderDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import com.khanhisdev.movieservice.entity.Showtime;
import com.khanhisdev.movieservice.utils.MapperUtils;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(uses = {MapperUtils.class})
public interface ShowtimeMapper extends GenericMapper<Showtime,ShowtimeRequestDto,ShowtimeResponseDto>{
    ShowtimeForOrderDto mapToResponseOrderDto(Showtime showtime);
}
