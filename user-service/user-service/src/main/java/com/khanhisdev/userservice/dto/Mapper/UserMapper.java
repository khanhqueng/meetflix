package com.khanhisdev.userservice.dto.Mapper;

import com.khanhisdev.userservice.dto.Model.UserDto;
import com.khanhisdev.userservice.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Getter
@Setter
@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;
    public User mapToEntity(UserDto userDto){
        User user= mapper.map(userDto, User.class);
        return user;
    }
    public UserDto mapToDto(User user){
        UserDto userDto= mapper.map(user, UserDto.class);
        return userDto;
    }
}
