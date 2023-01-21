package com.blog.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogapp.entities.User;
import com.blog.blogapp.exceptions.ResourceNotFoundException;
import com.blog.blogapp.payloads.UserDto;
import com.blog.blogapp.repositories.UserRepo;
import com.blog.blogapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
            "User"," id ",userId
        ));

        user.setId(userDto.getId());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        User savedUser = userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
            "User"," id ",userId
        ));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        //  List<UserDto> userDto = new ArrayList<UserDto>();
         List<User> allUsers = this.userRepo.findAll();

         List<UserDto> collectedUserDto = allUsers.stream().
         map(user -> this.userToDto(user)).
         collect(Collectors.toList());

        //  Alternative approach
        //  allUsers.stream().forEach((user) -> {
        //     userDto.add(this.userToDto(user));
        //  });

        return collectedUserDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
            "User"," id ",userId
        ));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto) {
        // User user = new User();
        // user.setId(userDto.getId());
        // user.setAbout(userDto.getAbout());
        // user.setEmail(userDto.getEmail());
        // user.setName(userDto.getName());
        // user.setPassword(userDto.getPassword());
        // return user;

        // using model mapper dependency
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user) {
        // UserDto userDto = new UserDto();
        // userDto.setId(user.getId());
        // userDto.setAbout(user.getAbout());
        // userDto.setEmail(user.getEmail());
        // userDto.setName(user.getName());
        // userDto.setPassword(user.getPassword());
        // return userDto;

        // using model mapper dependecy
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;

    }

}
