package com.blog.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogapp.payloads.ApiResponse;
import com.blog.blogapp.payloads.UserDto;
import com.blog.blogapp.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    //Create User
    @PostMapping ("/")
    public ResponseEntity<UserDto> cretaeUser(@RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
        // with new Keyword
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // update User

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uId) {
        UserDto updatedUser = this.userService.updateUser(userDto, uId);
        // without new keyword 
        return ResponseEntity.ok(updatedUser);
    }

    // delete User

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser( @PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>
        (new ApiResponse("user deleted succeessfully", true), HttpStatus.OK);
    }

    //get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
