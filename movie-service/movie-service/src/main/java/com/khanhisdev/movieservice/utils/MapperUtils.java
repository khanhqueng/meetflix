package com.khanhisdev.movieservice.utils;

import com.khanhisdev.movieservice.entity.Movie;
import com.khanhisdev.movieservice.entity.ProjectionRoom;
import com.khanhisdev.movieservice.entity.Theater;
import org.mapstruct.Mapper;

@Mapper
public abstract class MapperUtils {
    public String movieToMovieName(Movie movie){
        return movie.getName();
    }
    public String theaterToTheaterName(Theater theater){
        return theater.getName();
    }
    public Integer projectionRoomToRoomName(ProjectionRoom projectionRoom){
        return projectionRoom.getNumber();
    }
}
