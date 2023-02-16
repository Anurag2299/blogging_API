package com.blog.blogapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.blogapp.entities.User;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.repositories.UserRepo;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // loading user from DB by user name
        User user = this.userRepo.findByEmail(username).orElseThrow(
            () -> new ResourceNotFoundException("User", "email"+ username, 0)
        );
        return user;
    }
    
}
