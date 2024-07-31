package com.khanhisdev.userservice.controller;

import com.khanhisdev.userservice.dto.RequestDto.CommentDto;
import com.khanhisdev.userservice.dto.ResponseDto.CommentResponseDto;
import com.khanhisdev.userservice.service.CommentService;
import com.khanhisdev.userservice.utils.CustomHeaders;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByMovieId(@PathVariable(name = "movieId") Long movieId){
        return new ResponseEntity<>(commentService.getAllCommentsByMovieId(movieId), HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByUserId(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id){
        return new ResponseEntity<>(commentService.getAllCommentsByUserId(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id ){
        return new ResponseEntity<>(commentService.createComment(commentDto, id), HttpStatus.OK);
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                    @RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id ,
                                                    @PathVariable(name = "commentId") Long commentId){
        return new ResponseEntity<>(commentService.updateComment(commentDto, id,commentId), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id ,
                                                    @PathVariable(name = "commentId") Long commentId){
        commentService.deleteComment(id,commentId);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
