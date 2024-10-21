package com.khanhisdev.movieservice.dto.Mapper;

import com.khanhisdev.movieservice.dto.RequestDto.MovieRequestDto;
import com.khanhisdev.movieservice.dto.ResponseDto.MovieResponseDto;
import com.khanhisdev.movieservice.entity.Movie;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
public interface MovieMapper extends GenericMapper<Movie, MovieRequestDto,MovieResponseDto>{

}
