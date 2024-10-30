package com.khanhisdev.userservice.dto.Mapper;

import com.khanhisdev.userservice.dto.RequestDto.CommentDto;
import com.khanhisdev.userservice.dto.ResponseDto.CommentResponseDto;
import com.khanhisdev.userservice.entity.Comment;
import com.khanhisdev.userservice.utils.MapperUtils;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(uses = {MapperUtils.class})
public interface CommentMapper extends GenericMapper<Comment,CommentDto,CommentDto> {
    @Mapping(target = "username", source = "comment.userId")
    CommentResponseDto mapToResponseDto(Comment comment);
}
