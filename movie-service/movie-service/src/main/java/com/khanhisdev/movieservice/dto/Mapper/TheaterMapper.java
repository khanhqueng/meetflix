package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.TheaterRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.TheaterResponseDto;
import com.khanhisdev.movieservice.entity.Theater;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
public interface TheaterMapper extends GenericMapper<Theater, TheaterRequestDto, TheaterResponseDto>{

}
