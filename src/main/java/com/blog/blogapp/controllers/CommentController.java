package com.blog.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.payloads.ApiResponse;
import com.blog.blogapp.payloads.CommentDto;
import com.blog.blogapp.services.CommentService;


@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){
        CommentDto createdComment = this.commentService.createComment(comment, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment( @PathVariable Integer commentId){
        this.commentService.deleteComment( commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully", true), HttpStatus.OK);
    }
}
