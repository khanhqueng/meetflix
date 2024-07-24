package com.khanhisdev.userservice.service.impl;

import com.khanhisdev.userservice.dto.Mapper.CommentMapper;
import com.khanhisdev.userservice.dto.RequestDto.CommentDto;
import com.khanhisdev.userservice.dto.ResponseDto.CommentResponseDto;
import com.khanhisdev.userservice.entity.Comment;
import com.khanhisdev.userservice.entity.User;
import com.khanhisdev.userservice.exception.MovieAPIException;
import com.khanhisdev.userservice.exception.ResourceNotFoundException;
import com.khanhisdev.userservice.repository.CommentRepository;
import com.khanhisdev.userservice.repository.UserRepository;
import com.khanhisdev.userservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private CommentMapper mapper;
    private UserRepository userRepository;
    @Override
    public List<CommentResponseDto> getAllCommentsByMovieId(Long id) {
        if(!commentRepository.existsByMovieId(id)){
            throw new ResourceNotFoundException("Movie","Id",id);
        }
        List<Comment> comments= commentRepository.findByMovieId(id);
        return comments.stream().map(comment -> mapper.mapToResponseDto(comment)).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponseDto> getAllCommentsByUserId(Long id) {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User","Id",id);
        }
        List<Comment> comments= commentRepository.findByUserIdId(id);
        return comments.stream().map(comment -> mapper.mapToResponseDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long userId) {
        Comment comment= mapper.mapToEntity(commentDto);
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
        comment.setUserId(user);
        return mapper.mapToDto(commentRepository.save(comment));

    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Long userId, Long commentId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        Comment comment= commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        if(!comment.getUserId().getId().equals(user.getId())){
            throw new MovieAPIException(HttpStatus.BAD_REQUEST,"Comment not belong to this user");
        }
        comment.setContent(commentDto.getContent());
        return mapper.mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long id, Long userId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        Comment comment= commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment", "id", id));
        if(!comment.getUserId().getId().equals(user.getId())){
            throw new MovieAPIException(HttpStatus.BAD_REQUEST,"Comment not belong to this user");
        }
        commentRepository.delete(comment);
    }
}
