package com.blog.blogapp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapp.entities.Category;
import com.blog.blogapp.entities.Post;
import com.blog.blogapp.entities.User;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payloads.PostDto;
import com.blog.blogapp.repositories.CategoryRepo;
import com.blog.blogapp.repositories.PostRepo;
import com.blog.blogapp.repositories.UserRepo;
import com.blog.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User userData = this.userRepo.findById(userId).orElseThrow(() -> new 
        ResourceNotFoundException("User", "ID", userId));

        Category categoryData = this.categoryRepo.findById(categoryId).orElseThrow(() -> new 
        ResourceNotFoundException("Category", "ID", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(userData);
        post.setCategory(categoryData);

       Post newPost = this.postRepo.save(post);
       return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post postDataById = this.postRepo.findById(postId).orElseThrow(() -> new 
        ResourceNotFoundException("Post", "ID", postId));

        postDataById.setTitle(postDto.getTitle());
        postDataById.setContent(postDto.getContent());
        postDataById.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(postDataById);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post postData = this.postRepo.findById(id).orElseThrow(() -> new 
        ResourceNotFoundException("Post", "ID", id));
        this.postRepo.delete(postData);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> postData = this.postRepo.findAll();
        return postData.stream().map((data) -> this.modelMapper.
        map(data, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post postData = this.postRepo.findById(id).orElseThrow(() -> new 
        ResourceNotFoundException("Post", "ID", id));
        return this.modelMapper.map(postData, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category categoryData = this.categoryRepo.findById(categoryId).orElseThrow(() -> new 
        ResourceNotFoundException("Category", "ID", categoryId));
        List<Post> postDataByCategory = this.postRepo.findByCategory(categoryData);
        return postDataByCategory.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User userData = this.userRepo.findById(userId).orElseThrow(() -> new 
        ResourceNotFoundException("User", "ID", userId));
        List<Post> postDataByUser = this.postRepo.findByUser(userData);
        return postDataByUser.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<Post> searchPost(String Keyword) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
