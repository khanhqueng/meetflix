package com.khanhisdev.userservice.utils;

import com.khanhisdev.userservice.entity.LikedMovie;
import com.khanhisdev.userservice.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class MapperUtils {
    public String mapUserToUserName(User user){
        return user.getUsername();
    }
    public List<Long> mapLikedMovieToId(List<LikedMovie> movie){
        if( movie==null) return null;
        return movie.stream().map(LikedMovie::getMovieId).toList();
    }
}
