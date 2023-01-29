package com.blog.blogapp.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapp.entities.Comment;
import com.blog.blogapp.entities.Post;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payloads.CommentDto;
import com.blog.blogapp.repositories.CommentRepo;
import com.blog.blogapp.repositories.PostRepo;
import com.blog.blogapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post postDataById = this.postRepo.findById(postId).orElseThrow(() -> new 
        ResourceNotFoundException("Post", "ID", postId));
        
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(postDataById);

        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        
        Comment commentById = this.commentRepo.findById(commentId).
        orElseThrow(() -> new ResourceNotFoundException
        ("Comment", "Id", commentId));

        this.commentRepo.delete(commentById);
        
    }
    
}
