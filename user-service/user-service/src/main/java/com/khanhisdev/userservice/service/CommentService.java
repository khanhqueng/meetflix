package com.khanhisdev.userservice.service;

import com.khanhisdev.userservice.dto.RequestDto.CommentDto;
import com.khanhisdev.userservice.dto.ResponseDto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getAllCommentsByMovieId(Long id);
    List<CommentResponseDto> getAllCommentsByUserId(Long id);
    CommentDto createComment(CommentDto commentDto, Long userId);
    CommentDto updateComment(CommentDto commentDto, Long userId, Long commentId);
    void deleteComment(Long id, Long userId);

}
