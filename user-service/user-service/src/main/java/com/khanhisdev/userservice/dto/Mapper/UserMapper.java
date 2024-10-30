package com.khanhisdev.userservice.dto.Mapper;

import com.khanhisdev.userservice.dto.RequestDto.UserDto;
import com.khanhisdev.userservice.dto.ResponseDto.UserResponseDto;
import com.khanhisdev.userservice.entity.User;
import com.khanhisdev.userservice.utils.MapperUtils;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Mapper(uses = {MapperUtils.class, CommentMapper.class})
public interface UserMapper extends GenericMapper<User,UserDto,UserDto>{
     UserResponseDto mapToResponseDto(User user);

     @Override
     @Mapping(target = "movies_id", source = "entity.movieId")
     @Mapping(target = "comments", source = "entity.comments")
     UserDto mapToDto(User entity);
}
