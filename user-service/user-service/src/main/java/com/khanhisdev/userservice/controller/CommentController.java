package com.khanhisdev.userservice.controller;

import com.khanhisdev.userservice.dto.RequestDto.CommentDto;
import com.khanhisdev.userservice.dto.ResponseDto.CommentResponseDto;
import com.khanhisdev.userservice.service.CommentService;
import com.khanhisdev.userservice.utils.CustomHeaders;
import com.netflix.discovery.converters.Auto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Tag(name = "Comment controller")
public class CommentController {
    private final CommentService commentService;
    @Operation(summary = "Get all comments by Movie Id", description = "API for get all comments about a movie")
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByMovieId(@PathVariable(name = "movieId") Long movieId){
        return new ResponseEntity<>(commentService.getAllCommentsByMovieId(movieId), HttpStatus.OK);
    }
    @Operation(summary = "Get all comments by user Id", description = "API for get all comments by an user")
    @GetMapping("/user")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByUserId(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id){
        return new ResponseEntity<>(commentService.getAllCommentsByUserId(id), HttpStatus.OK);
    }
    @Operation(summary = "User comments to a movie", description = "API for user comment a movie")
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id ){
        return new ResponseEntity<>(commentService.createComment(commentDto, id), HttpStatus.OK);
    }
    @Operation(summary = "User edit comment", description = "API for edit a comment by user")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                    @RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id ,
                                                    @PathVariable(name = "commentId") Long commentId){
        return new ResponseEntity<>(commentService.updateComment(commentDto, id,commentId), HttpStatus.OK);
    }
    @Operation(summary = "Delete a comment", description = "Api for user to delete their comment")
    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id ,
                                                    @PathVariable(name = "commentId") Long commentId){
        commentService.deleteComment(id,commentId);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
