package com.khanhisdev.userservice.dto.Mapper;

import com.khanhisdev.userservice.dto.RequestDto.CommentDto;
import com.khanhisdev.userservice.dto.ResponseDto.CommentResponseDto;
import com.khanhisdev.userservice.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CommentMapper {
    @Autowired
    private ModelMapper mapper;
    public Comment mapToEntity(CommentDto commentDto){
        Comment comment= mapper.map(commentDto, Comment.class);
        return comment;
    }
    public CommentDto mapToDto(Comment comment){
        CommentDto commentDto= mapper.map(comment, CommentDto.class);
        return commentDto;
    }
    public CommentResponseDto mapToResponseDto(Comment comment){
        CommentResponseDto commentResponseDto= mapper.map(comment, CommentResponseDto.class);
        return commentResponseDto;
    }
}
