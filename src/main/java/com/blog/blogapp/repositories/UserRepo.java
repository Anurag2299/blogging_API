package com.blog.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogapp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);
}
