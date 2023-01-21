package com.blog.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    
}
