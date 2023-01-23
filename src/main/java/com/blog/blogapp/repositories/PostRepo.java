package com.blog.blogapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.entities.Category;
import com.blog.blogapp.entities.Post;
import com.blog.blogapp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

}
