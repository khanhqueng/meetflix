package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.commons.dto.ShowtimeForOrderDto;
import com.khanhisdev.movieservice.dto.RequestDto.ShowtimeRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.ShowtimeResponseDto;
import com.khanhisdev.movieservice.entity.Showtime;
import com.khanhisdev.movieservice.utils.MapperUtils;
import org.mapstruct.Mapper;

@Mapper(uses = {MapperUtils.class})
public interface ShowtimeMapper extends GenericMapper<Showtime,ShowtimeRequestDto,ShowtimeResponseDto>{
    ShowtimeForOrderDto mapToResponseOrderDto(Showtime showtime);
}
