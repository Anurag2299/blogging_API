package com.blog.blogapp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    
    private int id;

    @Email(message = "Email is m=not valid!!")
    private String email;

    @NotEmpty
    @Size(min =4, message = "user name msut be min 4 characters!!")
    private String name;

    @NotEmpty(message = "password is required")
    // @Pattern  regex pattern can be passed
    private String password;

    @NotEmpty
    private String about;
}
