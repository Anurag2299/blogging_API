package com.blog.blogapp.services;

import java.util.List;

import com.blog.blogapp.entities.Post;
import com.blog.blogapp.payloads.PostDto;

public interface PostService {
    //create

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update

    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer id);

    //get
    List<PostDto> getAllPost();

    PostDto getPostById(Integer id);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<Post> searchPost(String Keyword);
}
